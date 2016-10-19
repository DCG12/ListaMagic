package com.example.a46406163y.listamagic;


import android.net.Uri;

import java.io.IOException;

public class Cards2016 {

            private final String BASE_URL = "http://api.magicthegathering.io/v1/cards?page=311";

                    String getCartes(String cards) {
                   Uri builtUri = Uri.parse(BASE_URL)
                                    .buildUpon()
                                    .build();
                    String url = builtUri.toString();

                            try {
                           String JsonResponse = HttpUtils.get(url);
                           return JsonResponse;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                   return null;
               }
    }

}
