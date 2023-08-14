package com.example.puntualo.mocks

import com.example.puntualo.models.Restaurant

class RestaurantMock {/*
    companion object {
        val restaurantList = listOf<Restaurant>(
            Restaurant(
                "Av. Argetina A2, Lt. 4 - Nuevo Chimbote Jr. Manuel Villavicencio 544 - Chimbote, Urbanizacion Buenos Aires, Peru",
                "Cantonada",
                "926766278",
                "https://scontent.flim6-2.fna.fbcdn.net/v/t39.30808-6/273017661_2436685413135487_6431439598733598514_n.jpg?_nc_cat=109&cb=99be929b-59f725be&ccb=1-7&_nc_sid=be3454&_nc_eui2=AeGN1h1BpiHiusk9duqBezHBN1Cia4h2gv83UKJriHaC_-OMxdFVZTKwFoQmHbLTomJzd8I5220wCWJiJFiWLjZI&_nc_ohc=176AKQUF_0AAX-4QINH&_nc_zt=23&_nc_ht=scontent.flim6-2.fna&oh=00_AfB2wfl95nS3HberSHk5L2Gs-uR1deyJLRusb3nL2pzUgw&oe=64DB4B3E",
                "12345678912",
                "makis",
                "111111"
            ),
            Restaurant(
                "Av. Brasil y Av. Anchoveta, Chimbote, Peru",
                "El pescadito",
                "981052724",
                "https://scontent.flim6-3.fna.fbcdn.net/v/t39.30808-6/302695927_502463328546233_4875889111846318210_n.jpg?_nc_cat=108&cb=99be929b-59f725be&ccb=1-7&_nc_sid=be3454&_nc_eui2=AeF9sVEGs47PYuIAxgy9WG12AXLoqPQbnvMBcuio9Bue8_FS9gCqM2rGQJ2FyDf6kVln4tJq1R5gzmNCho2Cz-Br&_nc_ohc=BMc1-BxnaRwAX-KMNtq&_nc_zt=23&_nc_ht=scontent.flim6-3.fna&oh=00_AfB53KHGWtvyq4VSOk1MBDtlZSVU4uqr3_E-RhxYeK4PJg&oe=64DBCAA6",
                "98765432198",
                "comida marina",
                "222222"
            ),
            Restaurant(
                "Av. Country Mz L Lt 1, Nuevo Chimbote, Peru",
                "Chifa Hugos",
                "920222523",
                "https://scontent-lim1-1.xx.fbcdn.net/v/t39.30808-6/294857176_539923031254414_5338694468502053710_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeG1zrPTkYBW3aWTtdu-lyM5nrZVD1kg8kKetlUPWSDyQnwFOUp4EyS2w-N2qV7GOGJ9nOcJ5_K3r6DLcYUTjiWh&_nc_ohc=nu0D9LHj6R0AX-z5RUE&_nc_ht=scontent-lim1-1.xx&oh=00_AfBHRHgai5uZSgOZ0dxVN6e7Nr3rtc3UKDjVl5nrYn6bCg&oe=64D06691",
                "20600134010",
                "Chifa",
                "555555"
            ),
            Restaurant(
                "Av.Pacifico 350, Nuevo Chimbote, Peru",
                "Napos Chicken",
                "941189642",
                "https://scontent-lim1-1.xx.fbcdn.net/v/t39.30808-6/327284272_1239539156939048_8669284564066087809_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeF28vOeRKm_xoX9ubHVLwpzlXahKeIiYjCVdqEp4iJiMLLr7rcMf0L9Dd7RmB5Jtc2xqgARyWJdVEwPz0N5jb4F&_nc_ohc=IME_7qEkNzcAX-13K_a&_nc_ht=scontent-lim1-1.xx&oh=00_AfCNdoYc591-7Q44nM3Ml7bcek3NFH9Tl6Fy8bkYUKuK0A&oe=64D09858",
                "20532038686",
                "Polleria",
                "666666"
            ),
            Restaurant(
                "Av. Universitaria, Nicolas Garatea Mz 3 Lote 25, Chimbote, Peru",
                "La Avenida",
                "902819244",
                "https://scontent.flim1-1.fna.fbcdn.net/v/t39.30808-6/332776273_581864903855716_2783218856628098350_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeENU1IyYr27ucf-iYMPAhbm15EIc8Z1kNbXkQhzxnWQ1i2tEZyNIJjSgdcg5xz0t0-5pEJ2-fhX4IvDZI-rPc0z&_nc_ohc=6OHESl6SwYUAX-pRDSn&_nc_ht=scontent.flim1-1.fna&oh=00_AfCxy3bsUyZHtPPPYAZfBl7Xx6CEelyYKaWXrOg6u1lmoQ&oe=64D037E5",
                "13121110987",
                "rooftop",
                "777777"
            ),
            Restaurant(
                "Av. Anchoveta y Av. Brasil, Chimbote, Peru",
                "Rico Chimbote",
                "999146321",
                "https://scontent.flim1-2.fna.fbcdn.net/v/t39.30808-6/309274114_467033755462588_8816633588223106993_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeGV5USZRnNizUstjz2i1Akjo4mdVSVSESSjiZ1VJVIRJN6IobdtBQAB57FkSfoOTvczu0SX1IYReEGYa_AwGO_Q&_nc_ohc=ohW2NqRXUGYAX9vd4x-&_nc_oc=AQlpsZ7JZ_12zmAAvnp2vf_V0Dtce3MXX57E2HIHB34Xtnt07uP4n1v14ittnWt1rnc&_nc_ht=scontent.flim1-2.fna&oh=00_AfDfV77mv4-NdB2McvVMtOhk2hr4e4L92eFKbmFHaHkufg&oe=64D1ABF4",
                "20445776310",
                "comida marina",
                "888888"
            ),
            Restaurant(
                "Av Anchoveta, Urb. Santa Rosa Mz. C1 Lt-10, Chimbote, Peru",
                "El Picantito",
                "968765353",
                "https://scontent.flim1-1.fna.fbcdn.net/v/t39.30808-6/364748466_778278230966206_2878338914677720008_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeGEaE6Y4Mi8mN-ww92CKgO_C6uSihn0C1cLq5KKGfQLV3LQ0KEHNdamsARGzkWnMAmlSZDfrgca0VARMwbmu7B4&_nc_ohc=G1AvoklTbsIAX8TfFOr&_nc_ht=scontent.flim1-1.fna&oh=00_AfA0lVtNweRIx0LdMvuMDiroB46qjPoc_VAznhbVXzeoVA&oe=64D10844",
                "20600789636",
                "Comida Marina",
                "999999"
            ),
            Restaurant(
                "Av. Brasil Mz. A Lt. 2A- Los Alamos- Nvo Chimbote, Chimbote, Peru",
                "Waykis",
                "968878711",
                "https://scontent.flim1-2.fna.fbcdn.net/v/t39.30808-6/340948447_868441910918437_6158738208543843528_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeFYG5DzLsPZyQfqpTQsqZdnYQ85Z8wBKwJhDzlnzAErAgVleGf8ScDDycyu9PTB11JNFW9YDwYtOhZrEGuRZg7t&_nc_ohc=ShJ3YYfwI2kAX-GstaW&_nc_ht=scontent.flim1-2.fna&oh=00_AfCPxSq21IHfS-Zds0Gqzys8wl01sQ9ofwDfYpRawD67Bg&oe=64D183A9",
                "20550801516",
                "comida marina",
                "101010"
            ),
            Restaurant(
                "Jr, Jr. Alfonso Ugarte 797, Chimbote 02803",
                "Chifa ULLOA",
                "916446595",
                "https://lh3.googleusercontent.com/p/AF1QipP29z0ck3tekbdzIABLlrWZ6kjcGoLkZm5js8aM=s1360-w1360-h1020",
                "13121110988",
                "Comida asiatica",
                "1"
            ),
            Restaurant(
                "Jr. Alfonso Ugarte 668, Chimbote 02803",
                "CHIFA ORIENTAL",
                "982152342",
                "https://lh3.googleusercontent.com/p/AF1QipNIwPMC2gPK73NE3jeIT0Uv2gCBe16XJm2YHPEG=s1360-w1360-h1020",
                "13121110989",
                "Comida asiatica",
                "2"
            ),
            Restaurant(
                "Jr. Pachacutec Mz. 16, Chimbote 02711",
                "Cevichería Charito",
                "936696446",
                "https://lh3.googleusercontent.com/p/AF1QipMmqFYVYKIcKb-UpcdLuFDE8f6kWOxzOb7gMuj8=s1360-w1360-h1020",
                "13121110990",
                "Comida marina",
                "3"
            ),
            Restaurant(
                "165, Huayna Capac 283, Chimbote",
                "Tole restaurant",
                "(043)419738",
                "https://lh3.googleusercontent.com/p/AF1QipM7O4c7aYKOVyWe0mQXwJLf51EgTDlyu3lLEoev=s1360-w1360-h1020",
                "13121110991",
                "Comida peruana",
                "4"
            ),
        );
    }*/
}
