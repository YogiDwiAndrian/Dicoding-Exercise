# Laporan Proyek Machine Learning - Yogi Dwi Andrian

## Daftar Isi

-   [Project Overview](#project-overview)
-   [Business Understanding](#business-understanding)
-   [Data Understanding](#data-understanding)
-   [Data Preparation](#data-preparation)
-   [Modeling](#modeling)
-   [Evaluation](#evaluation)
-   [Referensi](#referensi)

## Project Overview
<p align="center">
  <img width="500" height="250" src="https://user-images.githubusercontent.com/56554261/137687558-1590fa93-e507-4774-90de-93b0f44b6654.jpg" alt="Sumber : https://www.cafeteria.id/2018/05/buku-adalah-jendela-dunia.html">
</p>

Pada proyek ini , akan dibuat sebuah sistem rekomendasi untuk buku yang akan dibaca. Buku adalah kumpulan kertas berisi informasi, tercetak, disusun secara sistematis, dijilid serta bagian luarnya diberi pelindung terbuat dari kertas tebal, karton atau bahan lain[[1](https://docplayer.info/82315383-B-p-sitepu-penulisan-buku-teks-pelajaran-bandung-pt-remaja-rosdakarya-2012-17-2.html)]. Buku merupakan sumber berbagai informasi yang dapat membuka wawasan kita tentang berbagai hal seperti ilmu pengetahuan, ekonomi, sosial, budaya, politik, maupun aspek-aspek kehidupan lainnya. Selain itu, dengan membaca, dapat membantu mengubah masa depan, serta dapat menambah kecerdasan akal dan pikiran kita[[2](http://sibopaksara.kemdikbud.go.id/artikel-detail/apa-sih-manfaat-membaca)]. Selain mempercedas otak, buku juga memiliki manfaat lainnya seperti dapat menstimulus otak, dapat mengurangi stress, dapat menambah wawasan dan pengetahuan, melatih ketramplilan untuk berfikir dan menganalisa, serta masih banyak lagi manfaatnya.

Namun disayangkan tingkat literasi indonesia menempati urutan kedua dari bawah soal literasi dunia. Menurut data dari UNESCO _(United Nations Educational, Scientific and Cultural Organization)_, minat baca masyarakat indonesia sangat memprihatinkan yaitu hanya 0,001% yang artinya dari 1,000 penduduk indonesia, cuma 1 orang/penduduk yang rajin membaca[[3](http://uis.unesco.org/en/country/id)]. Hal ini disebabkan dari berbagai faktor, dilansir dari website gramedia terdapat 5 penyebab yang mungkin bisa menjadi faktor alasan minat baca rendah saat ini antara lain disebabkan karena lingkungan sekitar, generasi yang serba instan, gadget, gim daring dan sosial media, serta diri sendiri[[4](https://www.gramedia.com/blog/5-penyebab-kurangnya-minat-baca-di-indonesia/)]. 

Dari beberapa faktor yang disebutkan terdapat faktor yang berpengaruh besar terhadap minat baca seseorang yaitu faktor diri sendiri karena yang menentukan tindakan kita yaitu rasa keingintahuan, rasa suka, minat, dan niat dalam diri kita sendiri. Salah satu hal yang dapat diterapkan untuk meningkatkan minat dan keinginan untuk membaca banyak buku yaitu buka buku berdasarkan minat atau hobi[[5](http://p4tkmatematika.kemdikbud.go.id/artikel/2011/10/04/cara-memunculkan-rasa-suka-keinginan-dan-minat-membaca-buku/)]. Oleh karena itu dibutuhkan sebuah sistem yang dapat memberikan rekomendasi buku sesuai minat atau berdasarkan riwayat buku yang dibaca. Hal ini dapat meningkatkan minat baca masyarakat indonesia agar memberikan wawasan yang lebih luas dan mencerdaskan masyarakat.

[← Kembali ke Daftar Isi](#daftar-isi)

## Business Understanding

### Problem Statements

Setelah mengetahui beberapa masalah diatas, berikut ini merupakan rincian masalah yang perlu diselesaikan di proyek ini:

-   Sistem rekomendasi apa yang baik untuk diterapkan pada kasus ini?
-   Bagaimana cara membuat sistem rekomendasi agar dapat menambah minat baca seseorang?

### Goals

Berikut adalah tujuan dari dibuatnya proyek ini:

-   Membuat sistem rekomendasi buku untuk pembaca.
-   Memberikan rekomendasi untuk buku yang kemungkinan akan dibaca oleh seseorang.

### Solution approach

Gammbar dibawah ini adalah diagram alir dari langkah-langkah yang dilakukan untuk melaksanakan proyek ini :

![Diagram Alir](https://user-images.githubusercontent.com/56554261/138203208-a428a547-0222-4018-9e2c-ec8b1c4af412.png)

Solusi yang dapat dilakukan untuk memenuhi tujuan dari proyek ini diantaranya :

-   Untuk bagian pra-pemrosesan data dilakukan beberapa teknik diantaranya :

    -   Menyalin data yang kosong dalam kolom _original_title_ dari kolom _title_.
    -   Membersihkan data duplikasi.

    Setelah hal tersebut dilakukan, selanjutnya dilakukan visualisasi data yang dapat dilihat lebih lengkap pada bagian _Data Understanding_.
    
-   Untuk bagian persiapan data (sebelum dimasukkan ke model) dilakukan beberapa teknik diantaranya :

    -   Pemilihan data untuk dimasukkan dalam _dataframe_ baru dari kolom _original_title_ dan _authors_
    -   Menemukan representasi fitur penting dari setiap kolom _authors_ dengan TF-IDF Vectorizer.
    
    Penjelasan lengkap mengenai persiapan data dapat dilihat lebih lengkap pada bagian _Data Preparation_.
    
-   Kemudian untuk sistem rekomendasi yang dibuat, dipilih sistem rekomendasi _content based filtering_ karena sesuai dengan datasetnya dan mudah diterapkannya. Sehingga sistem rekomendasi dibuat untuk memberikan rekomendasi pada pembaca terhadap buku yang sebelumnya dibaca. Beberapa algoritma yang digunakan untuk membuat sistem rekomendasi di proyek ini diantaranya :

    -   Dengan _cosine similarity_. Algoritma ini dipilih karena mudah digunakan dan banyak referensinya. _Cosine similarity_ mengukur kesamaan antara dua vektor dan menentukan apakah kedua vektor tersebut menunjuk ke arah yang sama. Ia menghitung sudut cosinus antara dua vektor. Semakin kecil sudut cosinus, semakin besar nilai cosine similarity. Cara menghitungnya adalah dengan rumus berikut ini:
    
    ![cosine similarity](https://user-images.githubusercontent.com/56554261/138207344-a42107e3-ea26-49f0-93e5-e2288f0d8882.png)

    Dimana nilai x, y adalah nilai vektor dan k adalah nilai _cosine similarity_ dari vektor x dan y.
    
    -   Dengan model, yakni algoritma K-Nearest Neighbor. Algoritma ini sangatlah sederhana, bekerja dengan berdasarkan pada jarak terpendek dari _sample_ uji ke sample latih untuk menentukan k-NN nya. Setelah mengumpulkan k-NN, kemudian diambil mayoritas dari k-NN untuk dijadikan prediksi dari _sample_ uji. Data untuk algoritma k-NN terdiri dari beberapa atribut multi-variate Xi yang akan digunakan untuk mengklasifikasikan Y. Data dari k-NN dapat dalam skala ukuran apapun, dari _ordinal_ ke _nominal_. Perhitungan jarak yang paling umum dipakai pada perhitungan pada algoritma k-NN adalah menggunakan perhitungan jarak Euclidean. Rumusannya adalah sebagai berikut:
       
       ![K-Nearest Neighbor](https://user-images.githubusercontent.com/56554261/139202195-7d6a2c47-83c4-44f8-a977-6e903555166e.PNG)
       
       Dimana :
      - xi = sampel data
      - yi = data uji atau data testing
      - i = variabel data
      - d(x,y) = dissimilarity/jarak
      - n = dimensi data


       Selain itu, berikut kelebihan dan kekurangan dari algoritma ini : 
    
       - Kelebihan algoritma _K-Nearest Neighbor (KNN)_:
         - Algoritmanya yang mudah digunakan dan sederhana.
         - Algoritmanya sangat fleksibel, dapat diimplementasikan pada kasus regresi, klasifikasi dan pencarian.

       - Kekurangan algoritma _K-Nearest Neighbor (KNN)_:
         - Algoritme menjadi lebih lambat secara signifikan karena jumlah contoh dan/atau prediktor/variabel yang meningkat.

[← Kembali ke Daftar Isi](#daftar-isi)
    
## Data Understanding

![goodbooks-10k](https://user-images.githubusercontent.com/56554261/137752814-979c7bf0-bddb-4923-bb3c-c2066b273d25.png)

Tabel dibawah ini merupakan informasi dari dataset yang digunakan :

Jenis | Keterangan
--- | ---
Sumber | [Kaggle Dataset : goodbooks-10k](https://www.kaggle.com/zygmunt/goodbooks-10k)
Lisensi | CC BY-SA 4.0
Kategori | Literatur
Rating Penggunaan | 8.2 (Gold)
Jenis dan Ukuran Berkas | CSV (43 MB)
Nama Berkas | book_tags.csv, books.csv, ratings.csv, sample_book.xml, tags.csv, to_read.csv

Pada dataset yang digunakan terdapat beberapa file csv diantaranya adalah <code>book_tags.csv</code>, <code>books.csv</code>, <code>ratings.csv</code>, <code>sample_book.xml</code>, <code>tags.csv</code>, <code>to_read.csv</code>. Karena kita membuat sistem rekomendasi _content based filtering_ pada dataset di berkas <code>book.csv</code> sudah cukup komplit dan bisa untuk dijadikan dataset sistem rekomendasi. Berikut pratinjau dari dataset pada berkas <code>book.csv</code> :

<p align="left">
  <img height="250" src="https://user-images.githubusercontent.com/56554261/138207390-e25e255f-86d1-4ffa-b6ac-555e00a8f883.PNG" alt="Sumber : Pratinjau books.csv 1">
</p>
<p align="left">
  <img height="250" src="https://user-images.githubusercontent.com/56554261/138207472-4bd00aac-85a2-47a1-884b-ac2e639c9333.PNG" alt="Sumber : Pratinjau books.csv 2">
</p>
<p align="left">
  <img height="250" src="https://user-images.githubusercontent.com/56554261/138207487-f54bf310-b4f2-4345-a67a-7d18ee26457a.PNG" alt="Sumber : Pratinjau books.csv 3">
</p>

Kemudian gambar dibawah ini adalah informasi dataset pada berkas <code>books.csv</code> :

![Informasi books.csv](https://user-images.githubusercontent.com/56554261/138207913-a7b97107-6f76-4612-ab10-0e4a8c93f986.PNG)

variabel <code>books.csv</code> berisi informasi mengenai detail dari buku. Namun pada datanya masih terdapat banyak sekali nilai kosong seperti pada kolom <code>isbn</code>, <code>isbn13</code>, <code>original_publication_year</code>, <code>original_title</code> dan <code>language_code</code> 

Kemudian berikut adalah uraian variabel dari setiap kolom pada data : 

1. Kolom <code>book_id</code> merupakan kolom id dari masing-masing buku.

2. Kolom <code>best_book_id</code> edisi paling populer untuk karya tertentu.

3. Kolom <code>work_id</code> mengacu pada buku dalam arti abstrak.

4. Kolom <code>books_count</code> merupakan kolom jumlah edisi untuk suatu karya tertentu.

5. Kolom <code>isbn</code> ISBN _(International Standard Book Number)_ adalah kode pengidentifikasian buku yang bersifat unik.

6. Kolom <code>isbn13</code> sama dengan kolom isbn hanya saja isbn13 mempunyai 13 digit kode unik.

7. Kolom <code>authors</code> merupakan kolom nama dari penulis/pengarang buku.

8. Kolom <code>original_publication_year</code> berisi tahun penerbitan buku.

9. Kolom <code>original_title</code> merupakan kolom berisi judul buku.

10.  Kolom <code>title</code> merupakan kolom berisi judul buku dan hampir mirip dengan original title.

11.  Kolom <code>language_code</code> berisi kode bahasa buku

12.  Kolom <code>average_rating</code> merupakan kolom dari rata-rata rating buku.

13.  Kolom <code>ratings_count</code> berisi jumlah total pembaca yang memberikan rating.

14.  Kolom <code>work_ratings_count</code> tidak ada keterangan lebih lanjut mengenai kolom ini.

15.  Kolom <code>work_text_reviews_count</code> tidak ada keterangan lebih lanjut mengenai kolom ini.

16.  Kolom <code>ratings_1</code> kolom jumlah pembaca yang memberikan rating 1

17.  Kolom <code>ratings_2</code> kolom jumlah pembaca yang memberikan rating 2

18.  Kolom <code>ratings_3</code> kolom jumlah pembaca yang memberikan rating 3

19.  Kolom <code>ratings_4</code> kolom jumlah pembaca yang memberikan rating 4

20.  Kolom <code>ratings_5</code> kolom jumlah pembaca yang memberikan rating 5

21.  Kolom <code>image_url</code> berisi alamat gambar buku dengan ukuran normal

22.  Kolom <code>small_image_url</code> berisi alamat gambar buku dengan ukuran kecil

Berikut merupakan visualisasi data dari dataset yang digunakan dalamm proyek ini :

- 10 buku dengan rating teratas dari rata-rata rating tertinggi buku.
![newplot (17)](https://user-images.githubusercontent.com/56554261/138208810-86f45cff-6f76-4d07-bcb9-9f0091c616d9.png)

- 10 buku paling popular dari jumlah rating buku.
![newplot (18)](https://user-images.githubusercontent.com/56554261/138208826-0db8a1f6-cf70-46a1-9f7f-311f5c65f865.png)

- 6 penulis populer dari rata-rata rating tertinggimm.
![newplot (19)](https://user-images.githubusercontent.com/56554261/138208835-12abd805-4a3a-402a-af73-95075f86941f.png)

- Distribusi data numerik pada kolom <code>average_rating</code>
![newplot (20)](https://user-images.githubusercontent.com/56554261/138208855-5afa08b3-6490-4331-93e2-8147ee6efbc3.png)

[← Kembali ke Daftar Isi](#daftar-isi)

## Data Preparation

Seperti yang sudah dijelaskan pada bagian Solution approach, berikut adalah tahapan-tahapan dalam melakukan pra-pemrosesan data :

- Menyalin data yang kosong dalam kolom _original_title_ dari kolom _title_. Hal ini dikarenakan kedua kolom tersebut memiliki banyak kemiripan. Untuk kolom yang lain kita tidak melakukan manipulasi karena pada kolom lainnya merupakan kolom yang esensial bagi informasi buku dan nantinya kita tidak membutuhkan kolom tersebut.

- Membersihkan data duplikasi. Hal ini dikarenakan dapat menyebabkan munculnya data yang sama sebanyak 2 kali atau lebih pada sistem rekomendasi yang nantinya dibuat. Oleh karena itu data duplikasi ini perlu dihilangkan karena sebenarnya data tersebut sudah ada pada dataset. Proses ini dilakukan dengan menggunakan _method_ `drop_duplicates` pada _dataframe_ dari dataset.

<p align="center">
  <img src="https://user-images.githubusercontent.com/56554261/138209978-f3844d7a-6f9d-4d7f-b6f0-e736e2498545.PNG" alt="Sumber : duplikasi data">
</p>

- Pemilihan data untuk dimasukkan dalam _dataframe_ baru dari kolom _original_title_ dan _authors_. Hal ini dikarenakan dalam pembuatan proyek hanya dibutuhkan kedua kolom tersebut dan dapat memmpermudah dalam prosesing data selanjutnya dan mempercepat proses karena tidak harus menampung banyak data yang tidak diigunakanm.

- Menemukan representasi fitur penting dari setiap kolom _authors_ dengan TF-IDF Vectorizer. Dikarenakan data kita berupa teks dan pada proses pemodelan hanya bisa menerima masukan data numerik maka diibutuhkan cara mengubah data teks ke numerik. Pada proyek ini untuk mengubah data teks menjadi numerik digunakan teknik TF-IDF atau kepanjangannya adalah _Term Frequency-Inverse Document Frequency_. TF-IDF bertujuan untuk mengukur seberapa penting suatu kata terhadap kata-kata lain dalam dokumen. Pada proyek ini digunakan fungsi [tfidfvectorizer()](https://scikit-learn.org/stable/modules/generated/sklearn.feature_extraction.text.TfidfVectorizer.html) yang melakukan proses tokenisasi pada teks, mempelajari kosa kata, melakukan pembobotan frekuensi dokumen secara terbalik (inverse), dan memungkinkan untuk melakukan proses encoding teks baru. Dan hasil akhir dari teknik ini akan dirubah menjadi bentuk matriks untuk persiapan dimasukkan dalam pemodelan.

[← Kembali ke Daftar Isi](#daftar-isi)

## Modeling

Setelah dilakukan pra-pemrosesan data, selanjutnya adalah membuat sistem rekomendasi _content based filtering_.

1. Dengan _cosine similarity_

_Cosine similarity_ mengukur kesamaan antara dua vektor dan menentukan apakah kedua vektor tersebut menunjuk ke arah yang sama. Ia menghitung sudut cosinus antara dua vektor. Semakin kecil sudut cosinus, semakin besar nilai _cosine similarity_. Rekomendasi diberikan dengan menghitung _cosine similarity_ dari setiap data di dataset menggunakan fungsi [cosine_similarity](https://scikit-learn.org/stable/modules/generated/sklearn.metrics.pairwise.cosine_similarity.html) dari sklearn. Prosesnya adalah dengan memanggil fungsi `cosine_similarity` dengan argumen matrik sebagai objeknya. Untuk tahapan pemberian rekomendasinya, dibuat fungsi getRecommendedBooks_cosine dimana fungsi tersebut akan memberikan rekomendasi terhadap suatu nama buku dimana skenarionya adalah apabila pembaca memmbaca suatu buku, maka akan diberikan rekomendasi buku yang mungkin disukai/diinginkan pembaca juga dengan berdasarkan nama penulis yang sama/mirip dengan buku yang telah dibaca. Pada fungsi tersebut, akan dilakukan pencarian kolom dari suatu nama buku pada _dataframe_ baru hasil perhitungan _cosine similarity_. Dengan menggunakan argpartition, kita mengambil sejumlah nilai k tertinggi dari _similarity_ data dari dataframe. Kemudian, kita mengambil data dari bobot (tingkat kesamaan) tertinggi ke terendah. Data ini dimasukkan ke dalam variabel closest. Berikutnya, kita menghapus nama buku yang yang dicari agar tidak muncul dalam daftar rekomendasi.Hasil rekomendasinya adalah seperti berikut :

![Hasil rekomendasi cosine similarity](https://user-images.githubusercontent.com/56554261/138237881-f3511627-c6ee-4ce0-82fd-1837b230ad15.png)

2. Dengan model K-Nearest Neighbor

Selanjutnya untuk membangun model ini, digunakan fungsi [NearestNeighbor](https://scikit-learn.org/stable/modules/generated/sklearn.neighbors.NearestNeighbors.html) dari sklearn dengan parameter metriksnya yakni euclidian. Kemudian fungsi tersebut di inisiasikan sebagai model yang selanjutnya dilakukan _fitting_ terhadap data matriks dari hasil teknik TF-IDF. Setelah itu dibuat fungsi `getRecommendedBooks_knn` untuk memberikan rekomendasi terhadap suatu nama buku dengan skenario yang sama dengan nomor 1. Pada fungsi tersebut, akan diambil satu baris pada _dataframe_ ke _dataframe_ baru, setelah mendapatkan baris tersebut kolom pada nama penulis akan di transformasikan menjadi matriks mmenggunakan teknik TF-IDF. Setelah itu menghitung jarak terdekat dengan buku yang di baca dan diurutkan dari jarak yang paling dekat, selanjutnya menampilkan nama penulis berdasarkan nama-nama buku yang didapat dan memiliki jarak terdekat. Untuk lebih jelasnya hasil rekomendasi dapat dilihat seperti berikut ini :

![Hasil rekomendasi knn](https://user-images.githubusercontent.com/56554261/138242784-95dbc02a-13be-49b1-b888-bcd7f4cd76d2.PNG)

[← Kembali ke Daftar Isi](#daftar-isi)

## Evaluation

Untuk mengukur kinerja model pada proyek ini digunakan perhitungan manual. Dikarenakan hasilnya hampir mirip pada keduanya, maka diputuskan untuk mengevaluasi hasil dari _cosine similarity_ dengan menghitung skor _precision_ dan akurasi. Berikut keterangan rumus yang digunakan dalam perhitungan evaluasi hasil :

![TP, TN, FP, FN](https://user-images.githubusercontent.com/56554261/137361455-47b803b1-dd3e-4c16-b1e2-ebc7e41e5dd9.png)

Keterangan rumus :

- _True Positive_ (TP), yaitu jumlah data positif yang terklasifikasi dengan benar oleh sistem.

- _True Negative_ (TN), yaitu jumlah data negatif yang terklasifikasi dengan benar oleh sistem.

- _False Positive_ (FP), yaitu jumlah data negatif namun terklasifikasi salah oleh sistem.

- _False Negative_ (FN), yaitu jumlah data positif namun terklasifikasi salah oleh sistem.


1.  Evaluasi manual skor presisi

Presisi merupakan rasio prediksi benar positif dibandingkan dengan keseluruhan hasil yang diprediksi positf. Berikut rumus dari presisi :

![rumus _precision_](https://user-images.githubusercontent.com/56554261/137361550-a4a9e9ce-560d-4f3a-b89d-eb853d531bcc.png)

Presisi = (TP) / (TP+FP)

Dari hasil rekomendasi model, diketahui bahwa buku yang berjudul Harry Potter and the Prisoner of Azkaban ditulis oleh J.K. Rowling dan Mary GrandPré . Dari 5 buku yang direkomendasikan, semuanya memiliki penulis yang sama _(similar)_. Artinya, presisi sistem kita sebesar 5/5 atau 100%.

2.  Evaluasi manual skor akurasi

Akurasi merupakan rasio prediksi Benar (positif dan negatif) dengan keseluruhan data. Berikut rumus dari presisi :

![rumus akurasi](https://user-images.githubusercontent.com/56554261/137361306-a5b54a6c-9dbe-4c84-a2b4-2f6ad9d1316d.png)

Akurasi = (TP + TN ) / (TP+FP+FN+TN)

Dari rumus yang ada dan hasil dari model maka pada perhitungan akurasi adalah (5 + 0) / (5 + 0 + 0 + 0) = 1 atau 100%

[← Kembali ke Daftar Isi](#daftar-isi)

# Referensi

[[1](https://docplayer.info/82315383-B-p-sitepu-penulisan-buku-teks-pelajaran-bandung-pt-remaja-rosdakarya-2012-17-2.html)] Sitepu, B. (2012), Penulisan Buku Teks Pelajaran, Cet 1  Bandung: PT Remaja Rosdakarya.

[[2](http://sibopaksara.kemdikbud.go.id/artikel-detail/apa-sih-manfaat-membaca)] Monapa, P. (2018, Februari 20), Apa sih Manfaat Membaca?. Medium. http://sibopaksara.kemdikbud.go.id/artikel-detail/apa-sih-manfaat-membaca

[[3](http://uis.unesco.org/en/country/id)] UNESCO. (2018), _Literacy Rate_. Medium. _http://uis.unesco.org/en/country/id_

[[4](http://sibopaksara.kemdikbud.go.id/artikel-detail/apa-sih-manfaat-membaca)] Gramedia. (“tanpa tanggal”), _5 Penyebab Kurangnya Minat Baca di Indonesia_. Medium. _https://www.gramedia.com/blog/5-penyebab-kurangnya-minat-baca-di-indonesia/_

[[5](http://p4tkmatematika.kemdikbud.go.id/artikel/2011/10/04/cara-memunculkan-rasa-suka-keinginan-dan-minat-membaca-buku/)] PPPPTK Matematika. (2011, Oktober 11), _Cara Memunculkan Rasa Suka, Keinginan, dan Minat Membaca Buku_. Medium. _http://p4tkmatematika.kemdikbud.go.id/artikel/2011/10/04/cara-memunculkan-rasa-suka-keinginan-dan-minat-membaca-buku/_

[[6](https://towardsdatascience.com/machine-learning-basics-with-the-k-nearest-neighbors-algorithm-6a6e71d01761)] Harrison, O. (2019, July 14). _Machine Learning Basics with the K-Nearest Neighbors Algorithm_. Medium. _https://towardsdatascience.com/machine-learning-basics-with-the-k-nearest-neighbors-algorithm-6a6e71d01761_

[← Kembali ke Daftar Isi](#daftar-isi)

> **Ini adalah bagian akhir laporan**
