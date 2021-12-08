# Laporan Proyek Machine Learning - Yogi Dwi Andrian

## Domain Proyek

Domain proyek yang dipilih dalam proyek _machine learning_ ini adalah mengenai tentang **kesehatan** dengan judul proyek "Prediksi Kemungkinan Sesorang Dapat Menderita Stroke" 

-   Latar Belakang

<p align="center">
  <img width="512" height="286" src="https://user-images.githubusercontent.com/56554261/136577353-2b702af5-b7be-4249-8e23-f6c1e38802ad.png" alt="Sumber : http://p2ptm.kemkes.go.id/artikel-sehat/germas-cegah-stroke">
</p>

Menurut WHO _(The World Health Organization)_ definisi stroke adalah berkembang pesat tanda-tanda klinis gangguan fungsi otak fokal (atau global), dengan gejala berlangsung 24 jam atau lebih atau menyebabkan kematian, tanpa penyebab yang jelas selain dari vaskular[[1](http://www.who.int/healthinfo/statistics/bod_cerebrovasculardiseasestroke.pdf)]. Stroke adalah kondisi yang terjadi ketika pasokan darah ke otak terganggu atau berkurang akibat penyumbatan (stroke iskemik) atau pecahnya pembuluh darah (stroke hemoragik). Tanpa darah, otak tidak akan mendapatkan asupan oksigen dan nutrisi, sehingga sel-sel pada sebagian area otak akan mati. Kondisi ini menyebabkan bagian tubuh yang dikendalikan oleh area otak yang rusak tidak dapat berfungsi dengan baik. Faktor yang menyebabkan terjadinya stroke antara lain adalah faktor kesehatan, faktor gaya hidup, faktor usia bahkan faktor keturunan.

Diperkirakan 17,9 juta orang meninggal karena CVD (Penyakit kardiovaskular) pada 2019, mewakili 32% dari semua kematian global. Dari kematian tersebut, 85% disebabkan oleh serangan jantung dan stroke menurut laporan dari WHO _(The World Health Organization)_[[2](https://www.who.int/en/news-room/fact-sheets/detail/cardiovascular-diseases-(cvds))]. Berdasarkan laporan dari Pusat Data dan Informasi Kementrian Kesehatan RI, secara nasional prevalensi stroke di Indonesia tahun 2018 berdasarkan diagnosis dokter pada penduduk berumur diatas 15 tahun sebesar 10,9%, atau diperkirakan sebanyak 2.120.362 orang[[3](https://pusdatin.kemkes.go.id/download.php?file=download/pusdatin/infodatin/infodatin-stroke-dont-be-the-one.pdf)]. Maka dari itu diperlukan kesadaran bagi semua orang untuk menghindari resiko-resiko yang dapat menyebabkan penyakit stroke. Salah satunya pada proyek ini, dimana akan dibuat sebuah model _machine learning_ untuk mengklasifikasikan orang-orang yang beresiko terkena penyakit stroke atau tidak. Dengan adanya model ini diharapkan semua orang dapat mampu menjaga kesehatan agar tidak beresiko terkena penyakit stroke dengan melakukan gaya hidup sehat dan selalu menjaga kesehatan tubuh. Implementasinya model ini dapat dijalankan pada sebuah platform aplikasi web ataupun android.

## Business Understanding
  
### Problem Statements

Berdasarkan dari latar belakang yang sudah diuraikan diatas, berikut ini merupakan rincian masalah yang dapat diselesaikan pada proyek ini :

- Bagaimana cara melakukan pra-pemrosesan data stroke agar dapat digunakan dalam pembuatan model yang optimal?
- Bagaimana cara membuat model _machine learning_ untuk mengklasifikasikan orang yang beresiko terkena penyakit stroke atau tidak?

### Goals

Berikut adalah tujuan dari dibuatnya proyek ini :

-   Melakukan pra-pemrosesan data stroke yang baik agar dapat digunakan dalam membuat model.
-   Membuat model _machine learning_ untuk mengklasifikasikan data stroke yang beresiko terkena penyakit stroke atau tidak yang memiliki tingkat akurasi > 80%.

### Solution statements

Solusi yang dapat dilakukan untuk memenuhi tujuan dari proyek ini diantaranya :

-   Untuk pra-pemrosesan data dapat dilakukan beberapa teknik, diantaranya :

    -   Menyandikan nama label kategori ke dalam label numerik biner menggunakan teknik **_one-hot encoding_**.
    -   Melakukan **standardisasi data** pada semua fitur data.
    -   Mengatasi data yang kosong pada dataset dengan menggunakan metode  **_KNN Imputation_**.
    -   Mengatasi masalah yang data yang jumlahnya tidak seimbang dengan menggunakan teknik **_SMOTE (Synthetic Minority Oversampling Technique)_**.
    -   Melakukan **pembagian dataset** menjadi dua bagian dengan rasio 80% untuk data latih dan 20% untuk data uji.

    Poin pra-pemrosesan data akan dibahas lebih lanjut pada bagian `Data Preparation`.
    
-   Untuk pembuatan model pada proyek ini dibuat 2 model yang berbeda yaitu yang pertama menggunakan algoritma **Multi-layer Perceptron** dan yang kedua menggunakan algoritma **K-Nearest Neighbor**. Cara kerja algoritma **Multi-layer Perceptron** sebagai berikut :

    -   Input didorong maju melalui MLP dengan mengambil _dot product_ dari input tersebut dengan bobot-bobot yang ada di antara input layer dan hidden layer. _Dot product_ ini menghasilkan nilai pada lapisan tersembunyi.
    -   MLP menggunakan fungsi aktivasi di setiap lapisan yang dihitung. Dorong keluaran terhitung pada lapisan saat ini melalui salah satu fungsi aktivasi.
    -   Setelah output yang dihitung pada lapisan tersembunyi telah didorong melalui fungsi aktivasi, dorong ke lapisan berikutnya di MLP dengan mengambil  _dot product_ dengan bobot yang sesuai.
    -   Mengulangi langkah dua dan tiga sampai lapisan output tercapai.
    -   Pada lapisan output, perhitungan akan digunakan untuk algoritma _backpropagation_ yang sesuai dengan fungsi aktivasi yang dipilih untuk MLP (dalam kasus pelatihan) atau keputusan akan dibuat berdasarkan output (dalam kasus pengujian). 
    <p align="center">
      <img width="512" height="400" src="https://user-images.githubusercontent.com/56554261/137331911-16a54ec5-d3d8-46ed-86ef-3165910433c2.png" alt="Sumber : https://towardsdatascience.com/multilayer-perceptron-explained-with-a-real-life-example-and-python-code-sentiment-analysis-cb408ee93141">
    </p>
    
    Yang kedua adalah cara kerja algoritma **K-Nearest Neighbor** sebagai berikut (diterjemahkan dari [[4](https://towardsdatascience.com/machine-learning-basics-with-the-k-nearest-neighbors-algorithm-6a6e71d01761)]):
    
    - Muat datanya.
    - Inisialisasi K ke jumlah tetangga yang Anda pilih.
    - Untuk setiap contoh dalam data :
      - Hitung jarak antara contoh kueri dan contoh yang ada pada data tersebut dengan rumus euclidian distance :
      ![[Rumus Euclidian Distance]](https://user-images.githubusercontent.com/56554261/137337138-8d60cb7f-5ad9-43e0-9e80-556a3e7ae73c.png)
      - Tambahkan jarak dan indeks contoh ke koleksi terurut.
    - Urutkan kumpulan jarak dan indeks yang diurutkan dari terkecil ke terbesar (dalam urutan menaik) berdasarkan jarak.
    - Pilih entri K pertama dari koleksi yang diurutkan.
    - Dapatkan label entri K yang dipilih.
    - Kembalikan mode label K.

    Selain itu, berikut kelebihan dan kekurangan dari masing-masing algoritma : 
    
    - Kelebihan algoritma _Multi-layer Perceptron (MLP)_ :
      - Kemampuan untuk mempelajari model non-linear.
      - Kemampuan mempelajari model secara real-time.

    - Kekurangan algoritma _Multi-layer Perceptron (MLP)_ :
       - MLP membutuhkan penyetelan sejumlah hyperparameter seperti jumlah neuron tersembunyi, lapisan, dan iterasi.
       - MLP sensitif terhadap penskalaan fitur.

    - Kelebihan algoritma _K-Nearest Neighbor (KNN)_:
       - Algoritmanya yang mudah digunakan dan sederhana.
       - Algoritmanya sangat fleksibel, dapat diimplementasikan pada kasus regresi, klasifikasi dan pencarian.

    - Kekurangan algoritma _K-Nearest Neighbor (KNN)_:
       - Algoritme menjadi lebih lambat secara signifikan karena jumlah contoh dan/atau prediktor/variabel yang meningkat.

   Setelah semua model jadi maka nanti dilakukan perbandingan antar model dan menentukan model mana yang memiliki kinerja yang lebih baik untuk melakukan proses klasifikasi.

## Data Understanding

![Sampul Dataset](https://user-images.githubusercontent.com/56554261/136794877-1f9d2a27-1542-417d-9cbd-6d91072b1375.PNG)

Informasi dataset dapat dilihat pada tabel dibawah ini :

Jenis | Keterangan
--- | ---
Sumber | [Kaggle Dataset : Stroke Prediction Dataset](https://www.kaggle.com/fedesoriano/stroke-prediction-datase)
Lisensi | Data files © Original Authors
Kategori | kesehatan, kondisi kesehatan, Kesehatan Publik
Rating Penggunaan | 10.0 (Gold)
Jenis dan Ukuran Berkas | CSV (316.97 kb)

Pada berkas yang diunduh yakni `healthcare-dataset-stroke-data.csv` terdapat informasi data stroke yang berjumlah 5110. Memiliki data numerik dan data kategori yaitu :
  - **Data Kategori** :  gender, ever_married, work_type, residence_type, smoking_status
  - **Data Numerik Biner** : hypertension,heart_disease, stroke.
  - **Data Numerik Berkelanjutan** : age, avg_glucose_level, bmi
Terdapat data kosong pada kolom 'bmi'. Berkas pada 'healthcare-dataset-stroke-data.csv' memiliki 12 kolom diantaranya adalah : 
  1. `id`  Nomor identifikasi individu.
  2. `gender` Jenis kelamin individu.
  3. `hypertension` Parameter terkait kesehatan, apakah seseorang menderita hipertensi. Disimbolkan 0 jika tidak menderita hipertensi, dan 1 jika menderita hipertensi.
  4. `heart_disease` Parameter terkait kesehatan, apakah seseorang memiliki penyakit jantung. Disimbolkan 0 jika tidak memiliki penyakit jantung, dan 1 jika memiliki penyakit jantung.
  5. `ever_married` Informasi pribadi, apakah orang sudah menikah atau belum belum?
  6. `work_type` Sifat tempat kerja.
  8. `Residence_type` Tipe tempat tinggal.
  9. `avg_glucose_level` Tingkat glukosa rata-rata dalam darah untuk individu.
  10. `bmi` indeks massa tubuh individu
  11. `smoking_status` Informasi kebiasaan merokok individu saat ini.
  12. `stroke` Informasi apakah individu pernah mengalami stroke atau tidak. Disimbolkan 0 jika tidak pernah mengalami stroke, dan 1 jika individu pernah mengalami stroke.

Kemudian terdapat juga visualisasi data untuk setiap kolom dengan fitur numerik biner dan kategori seperti pada gambar dibawah ini :

![kolom Gender](https://user-images.githubusercontent.com/56554261/137339988-c0f46326-34f7-4233-b5ba-dda6ba323d31.png)
![kolom Hypertension](https://user-images.githubusercontent.com/56554261/137340490-323f6726-8133-4515-9d86-188a20b7f80c.png)
![kolom Heart_disease](https://user-images.githubusercontent.com/56554261/137340389-52394902-6192-4ad7-980a-df7a5b2e8d93.png)
![kolom Ever_married](https://user-images.githubusercontent.com/56554261/137340397-d5593ca6-b42d-400e-b690-0ec72a27d657.png)
![kolom Residence_type](https://user-images.githubusercontent.com/56554261/137340534-7abdb34a-51d4-4a41-a888-ed42d5b988e7.png)
![kolom Smoking_status](https://user-images.githubusercontent.com/56554261/137340544-58bb077f-749a-4029-a851-226db2feb361.png)
![kolom Work_type](https://user-images.githubusercontent.com/56554261/137340570-335591bf-9ade-4eab-9ac4-796f1489a89b.png)
![kolom Stroke](https://user-images.githubusercontent.com/56554261/137340976-3ea28332-69db-4420-bcc6-e8fdc66037ca.png)

Berikutnya visualisasi data untuk kolom dengan fitur numerik berkelanjutan seperti pada gambar dibawah ini :

![kolom Age](https://user-images.githubusercontent.com/56554261/137340598-3ae2fb5c-6ac3-4b73-9bc5-ecc1d58e1974.png)
![kolom Avg_glucose_level](https://user-images.githubusercontent.com/56554261/137340618-3b4b7328-e2f7-4f42-8a9a-a1a31965bf44.png)
![kolom Bmi](https://user-images.githubusercontent.com/56554261/137340637-4acdf424-a40e-4080-85e4-054094c74014.png)

Terakhir visualisasi korelasi fitur terhadap target(stroke) seperti gambar dibawah ini :

![Korelasi fitur)](https://user-images.githubusercontent.com/56554261/137342502-de6a7869-668f-4fe2-8367-6d1cd7ff1c34.png)

## Data Preparation

Seperti yang sudah disebutkan sebelumnya pada bagian Solution statements, berikut adalah tahapan-tahapan dalam melakukan pra-pemrosesan data :

-   Menyandikan nama label kategori ke dalam label numerik biner menggunakan teknik **_one-hot encoding_**.

Dikarenakan data pada dataset `stroke prediction` memiliki beberapa data kategorikal atau teks maka harus diubah menjadi data numerik dan tetap membuat algoritma atau model untuk dapat memahaminya. Untuk mengubah data kategorikal atau teks disini menggunakan teknik _one-hot encoding_, teknik _one-hot encoding_ adalah teknik yang merubah setiap nilai di dalam kolom menjadi kolom baru dan mengisinya dengan nilai biner yaitu 0 dan 1. Proses ini diperlukan karena model tidak bisa memproses data teks melainkan data numerik.

-   Melakukan **standardisasi data** pada semua fitur data.

Standarisasi data membuat semua fitur numerik berada dalam skala data yang sama dan dapat membuat komputasi dari pembuatan model dapat berjalan lebih cepat karena rentang datanya hanya antara 0-1. Untuk melakukan proses tersebut digunakan fungsi [MinMaxScaler](https://scikit-learn.org/0.24/modules/generated/sklearn.preprocessing.MinMaxScaler.html#sklearn.preprocessing.MinMaxScaler) yang rumusnya dapat dilihat seperti gambar dibawah ini

<img width="260" src="https://user-images.githubusercontent.com/56554261/137347463-108e6f96-8bd9-4cb3-97df-0cfbf829027c.png" alt="Rumus MinMaxScaler">

-   Mengatasi data yang kosong pada dataset dengan menggunakan metode  **_KNN Imputation_**.

![missing value](https://user-images.githubusercontent.com/56554261/137348519-9dc57ff9-168b-4cb1-8470-2bff6a8c8125.png)

Terdapat dataset yang kosong pada kolom `bmi`, maka untuk mengatasinya terdapat dua pilihan yaitu metode dengan menghapus data atau metode dengan menambahkan data. Pemilihan metode dengan menghapus data bukanlah hal yang bijak karena akan mengakibatkan model yang nantinya akan dibuat kehilangan banyak informasi. Sehingga dipilihlah cara untuk memanipulasi datanya, dengan mengisi data yang kosong dengan nilai rata-rata dari tetangga terdekat yang diukur dengan jarak Euclidean. Pada tahap ini digunakan fungsi [KNNImputer](https://scikit-learn.org/stable/modules/generated/sklearn.impute.KNNImputer.html) untuk mengganti data yang kosong.

-   Mengatasi masalah yang data yang jumlahnya tidak seimbang dengan menggunakan teknik **_SMOTE (Synthetic Minority Oversampling Technique)_**.

Dataset yang tidak seimbang pada data kategori akan menyebabkan model yang dibuat menjadi bias terhadap suatu kategori yang memiliki data lebih banyak. Oleh karena itu diperlukan teknik manipulasi data, dan yang digunakan di sini adalah teknik [SMOTE](https://imbalanced-learn.org/stable/references/generated/imblearn.over_sampling.SMOTE.html) _(Synthetic Minority Oversampling Technique)_. SMOTE mengadopsi teknik _K-Nearest Neighbors_ dalam membuat instance data baru jadi untuk prosesnya kurang lebih hampir sama dengan _KNN Imputation_.

-   Melakukan **pembagian dataset** menjadi dua bagian dengan rasio 80% untuk data latih dan 20% untuk data uji.

Agar dapat menguji performa dari model pada data sebenarnya, maka perlu dilakukan pembagian dataset kedalam dua atau tiga bagian. Pada proyek ini dilakukan dua bagian saja yakni pada data latih dan data uji dengan rasio 80:20. Data latih dilakukan sepenuhnya untuk melatih model, sedangkan data uji merupakan data yang belum pernah dilihat oleh model dan diharapkan model dapat memiliki performa yang sama baiknya pada data uji seperti pada data latih. Pada bagian ini dipastikan juga pembagian label kategorikal haruslah sama banyak pada data latih dan data uji. Pembagian dataset dilakukan dengan modul [train_test_split](https://scikit-learn.org/0.24/modules/generated/sklearn.model_selection.train_test_split.html#sklearn.model_selection.train_test_split) dari scikit-learn.

## Modeling

Setelah melakukan pra-pemrosesan data yang baik pada tahap modeling akan dilakukan pembuatan dua model sekaligus, yakni model dengan [_MLPClassifier_](https://scikit-learn.org/stable/modules/generated/sklearn.neural_network.MLPClassifier.html?highlight=classifier#sklearn.neural_network.MLPClassifier) dan [_KNeighborsClassifier_](https://scikit-learn.org/stable/modules/generated/sklearn.neighbors.KNeighborsClassifier.html?highlight=classifier#sklearn.neighbors.KNeighborsClassifier). Pada tahapan dalam pembuatan model pertama yakni membuat model dengan [_MLPClassifier_](https://scikit-learn.org/stable/modules/generated/sklearn.neural_network.MLPClassifier.html?highlight=classifier#sklearn.neural_network.MLPClassifier) yakni dilakukan kompilasi model dan memanggil fungsi fit untuk memulai proses pelatihan dengan parameter yaitu data _X_Train_ dan _Y_Train_ yang sudah dipersiapkan sebelumnya, setelah itu dilakukan pengujian dari model. Setelah pembuatan model pertama dilanjutkan membuat model yang kedua sebagai pembanding dari model [_MLPClassifier_](https://scikit-learn.org/stable/modules/generated/sklearn.neural_network.MLPClassifier.html?highlight=classifier#sklearn.neural_network.MLPClassifier) dengan membuat model  [_KNeighborsClassifier_](https://scikit-learn.org/stable/modules/generated/sklearn.neighbors.KNeighborsClassifier.html?highlight=classifier#sklearn.neighbors.KNeighborsClassifier). Pada pembuatan model kedua prosesnya mirip dengan pembuatan model yang pertama yaitu melakukan kompilasi model dan memanggil fungsi fit untuk memulai proses pelatihan dengan parameter yaitu data _X_Train_ dan _Y_Train_ yang sudah dipersiapkan sebelumnya, setelah itu dilakukan pengujian dari model. Cara kerja dari kedua algoritma tersebut sudah dijelaskan pada _solution statement_. Setelah dilakukan proses pelatihan dan pengujian dari kedua model maka didapatkan sebuah hasil seperti berikut ini :

![perbandingan metriks](https://user-images.githubusercontent.com/56554261/138219919-b0cd72ae-8edc-45b6-8c11-4c458d9561af.PNG)

Dari kedua hasil model diatas, kita dapat melihat bahwa performa masing-masing model sudah cukup baik, pada algoritma _Multi-Layer Perceptron_ memiliki performa yang lebih baik dengan keunggulan pada akurasi dan f1-_score_ daripada algoritma _K-Nearest Neighbors_.

- Model _Multi-layer Perceptron_

![performa model mlp](https://user-images.githubusercontent.com/56554261/138219747-2d65d83b-b6d2-4c9b-a771-3880ed6328b9.png)

- Model _K-Nearest Neighbor_

![performa model knn](https://user-images.githubusercontent.com/56554261/138219760-6d9b1826-a513-4357-931a-2c953efd1c18.png)


## Evaluation

Pada proyek ini, model yang dibuat merupakan kasus klasifikasi dan menggunakan metriks akurasi, _f1-score_, _recall_ dan _precision_. Pada gambar dibawah ini ditampilkan kembali hasil pengukuran pada kedua model yang dibuat dengan metriks akurasi, _f1-score_, _recall_ dan _precision_.

- Performa model _Multi-layer Perceptron_
 
![performa mlp](https://user-images.githubusercontent.com/56554261/138219235-8add0e5a-aae3-45fb-a67b-339271571070.PNG)

- Performa model _K-Nearest Neighbor_
 
![performa knn](https://user-images.githubusercontent.com/56554261/138219243-146f5f7b-62dd-4583-9898-1886f35c2820.PNG)

Kedua model memiliki performa yang cukup bagus terbukti sudah melewati target yang diinginkan. Pada model MLP memiliki performa lebih baik daripada model KNN.

- Akurasi

![rumus akurasi](https://user-images.githubusercontent.com/56554261/137361306-a5b54a6c-9dbe-4c84-a2b4-2f6ad9d1316d.png)

Akurasi adalah ukuran keakuratan model saat menggunakan data aktual untuk memprediksi data. Akurasi dapat dihitung dengan rumus di atas. Kelebihan dari metrik ini adalah sering digunakan untuk membuat model klasifikasi, baik itu klasifikasi dua kelas maupun kategori. Kerugian dari indikator ini adalah dapat "menyesatkan" data yang tidak seimbang.

- _Precision_

 _Precision_ adalah metrik dalam kasus klasifikasi, yang digunakan untuk menghitung efek model dalam memprediksi label positif terhadap semua label positif model. Jadi bagaimana cara menghitungnya, pertama kita perlu memahami istilah TP, TN, FP, FN. Deskripsi singkat ditunjukkan pada tabel di bawah ini.
 
 ![TP, TN, FP, FN](https://user-images.githubusercontent.com/56554261/137361455-47b803b1-dd3e-4c16-b1e2-ebc7e41e5dd9.png)
 
 Setelah memahaminya, kitapun dapat menghitungnya dengan rumus dibawah ini
 
 ![rumus _precision_](https://user-images.githubusercontent.com/56554261/137361550-a4a9e9ce-560d-4f3a-b89d-eb853d531bcc.png)
 
 Kelebihan dari metrik ini adalah fokusnya pada performa model (prediksi) untuk label data positif, sedangkan kelemahan metrik ini adalah tidak mempertimbangkan label negatif.
 
- _Recall_

_Recall_ adalah metrik dalam kasus klasifikasi, yang digunakan untuk menghitung efek model dalam memprediksi label positif untuk semua label data positif. Cara menghitungnya bisa dilihat pada rumus di bawah ini

![rumus _Recall_](https://user-images.githubusercontent.com/56554261/137361643-fc9c148f-9f20-49fb-8c8e-436fa2ef6b45.png)

Keuntungan metrik ini adalah menghitung bagian negatif dari prediksi label positif (tidak sama dengan akurasi). Tetapi kelemahannya adalah ketika semua prediksi = 1, _recall_ akan memiliki nilai 1 (prediksi negatif tidak dipertimbangkan).

- _f1-score_

_f1-score_ merupakan metrik dalam kasus klasifikasi yang digunakan untuk menghitung seberapa baik hasil prediksi model (_precision_) dan seberapa lengkap hasil prediksinya (_recall_). Cara menghitungnya dapat dilihat pada rumus dibawah ini

![rumus _f1-score_](https://user-images.githubusercontent.com/56554261/137361700-585421f2-538a-4865-acd3-d18ee53198ea.png)

Catatan : Nilai beta = 1 (f1-score)

Kelebihan dari metriks ini menutup semua kekurangan yang ada pada _precision_ dan _recall_. Namun kekurangannya adalah _f1-score_ tidak memperhitungkan hasil prediksi benar pada label negatif.

## _Referensi:_

[[1](http://www.who.int/healthinfo/statistics/bod_cerebrovasculardiseasestroke.pdf)] Truelsen, T. dan Begg, S. (2006), The Global Burden Of Cerebrovascular Disease, World Health Organization. 

[[2](https://www.who.int/en/news-room/fact-sheets/detail/cardiovascular-diseases-(cvds))] _Cardiovascular diseases (CVDs)_.(2021, Juni 11). who.int. Medium. https://www.who.int/en/news-room/fact-sheets/detail/cardiovascular-diseases-(cvds)

[[3](https://pusdatin.kemkes.go.id/download.php?file=download/pusdatin/infodatin/infodatin-stroke-dont-be-the-one.pdf)] Infodatin Stroke.(2019, November 29). pusdatin.kemkes.go.id. Medium. https://pusdatin.kemkes.go.id/article/view/20031000003/infodatin-stroke.html

[[4](https://towardsdatascience.com/machine-learning-basics-with-the-k-nearest-neighbors-algorithm-6a6e71d01761)] Harrison, O. (2019, July 14). _Machine Learning Basics with the K-Nearest Neighbors Algorithm_. Medium. https://towardsdatascience.com/machine-learning-basics-with-the-k-nearest-neighbors-algorithm-6a6e71d01761

**---Ini adalah bagian akhir laporan---**
