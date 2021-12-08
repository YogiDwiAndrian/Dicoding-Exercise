# -*- coding: utf-8 -*-
"""Submission_3_Kelas_Menengah_Machine_Learning_Developer.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1m_juU9Ptm3EntARV-c5XbQzdkEhY5iz_
"""

import tensorflow as tf
import os
import shutil
import pathlib
from keras_preprocessing.image import ImageDataGenerator
from keras.callbacks import EarlyStopping, ModelCheckpoint, ReduceLROnPlateau

from google.colab import drive
drive.mount('/content/gdrive')

import os
os.environ['KAGGLE_CONFIG_DIR'] = "/content/gdrive/My Drive/Kaggle"

# Commented out IPython magic to ensure Python compatibility.
#changing the working directory
# %cd /content/gdrive/My Drive/Kaggle

!kaggle datasets download -d pavelbiz/eyes-rtte
#unzipping the zip files and deleting the zip files
!unzip \*.zip  && rm *.zip

parent_dir = '/content/gdrive/MyDrive/Kaggle/'
path = os.path.join(parent_dir, 'eyesdataset')
os.mkdir(path)

shutil.move('/content/gdrive/MyDrive/Kaggle/femaleeyes', path)
shutil.move('/content/gdrive/MyDrive/Kaggle/maleeyes', path)

training_datagen = ImageDataGenerator(
    rescale=1. / 255,
    validation_split=0.2
)

WIDTH = 64
HEIGHT = 64
BATCH_SIZE = 32

train_generator = training_datagen.flow_from_directory(
        path,
        target_size=(WIDTH, HEIGHT), 
        batch_size=BATCH_SIZE,
        class_mode='binary',
        color_mode='rgb',
        subset='training')

validation_generator = training_datagen.flow_from_directory(
        path, 
        target_size=(WIDTH, HEIGHT), 
        batch_size=BATCH_SIZE,
        class_mode='binary',
        color_mode='rgb',
        subset='validation')

model = tf.keras.models.Sequential([
    tf.keras.layers.Conv2D(128, (3, 3), activation='relu', input_shape=(WIDTH, HEIGHT, 3)),
    tf.keras.layers.MaxPooling2D(2, 2),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Dropout(0.25),
    tf.keras.layers.Conv2D(64, (3, 3), activation='relu'),
    tf.keras.layers.MaxPooling2D(2, 2),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Dropout(0.25),
    tf.keras.layers.Conv2D(32, (3, 3), activation='relu'),
    tf.keras.layers.MaxPooling2D(2, 2),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Dropout(0.25),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(256,activation = "relu"),
    tf.keras.layers.Dropout(0.25),
    tf.keras.layers.Dense(1, activation='sigmoid')
])

model.compile(loss='binary_crossentropy',
              optimizer='Adam',
              metrics=['accuracy'])

save_model = 'model.h5'
earlyStopping = EarlyStopping(monitor='val_loss', patience=10, verbose=0, mode='min')
modelCheckpoint = ModelCheckpoint(save_model, save_best_only=True, monitor='val_accuracy', mode='max', verbose=1)
reducelr = ReduceLROnPlateau(monitor='val_loss', factor=0.1, patience=10, verbose=1, min_delta=1e-5, mode='min')

history = model.fit(
    train_generator,
    steps_per_epoch=len(train_generator.filenames) // BATCH_SIZE,
    validation_data=validation_generator,
    validation_steps=len(validation_generator.filenames) // BATCH_SIZE,
    epochs=10,
    callbacks=[earlyStopping, modelCheckpoint, reducelr],
    verbose=1)

import matplotlib.pyplot as plt

def plot_acc_loss(history):
    # Visualization Model Training
    loss = history.history['loss']
    val_loss = history.history['val_loss']
    acc = history.history['accuracy']
    val_acc = history.history['val_accuracy']

    epoch_number = range(len(acc))
    plt.figure(figsize=(6, 6), dpi=100)
    plt.subplot(2,1,1)
    plt.plot(epoch_number, acc, 'r', label='train acc')
    plt.plot(epoch_number, val_acc, 'b', label='val acc')
    plt.title('Train and Validation Accuracy')
    plt.legend()

    plt.subplot(2,1,2)
    plt.plot(epoch_number, loss, 'r', label='train loss')
    plt.plot(epoch_number, val_loss, 'b', label='val loss')
    plt.title('Train and Validation Loss')
    plt.legend()

    plt.show()

train_visualitation = plot_acc_loss(history)

converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()

tflite_model_file = pathlib.Path('/tmp/model.tflite')
tflite_model_file.write_bytes(tflite_model)