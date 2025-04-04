package com.sportclub.migracion_usuarios.commons.helpers;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class GeneratePrevNextPage {

    public enum Path {
        USUARIOS,
        SEDES
    }


    String host;
    Integer currentPage;
    Integer pageSize;
    Integer totalPages;
    Path path;
    Map<String, String> searchParams;


    public String[] nextPageAndPrev() {

        String[] res = new String[2];
        String pathBase = "";
        // Determinar la ruta base según el tipo de Path
        switch (path) {
            case USUARIOS:
                pathBase = "/usuarios?";
                break;
            case SEDES:
                pathBase = "/sedes?";
                break;

            default:
                break;
        }

        if (host.contains("localhost")) {
            host = "http://" + "localhost";
        }

        String baseUrlConstructor = null;
        StringBuilder urlWithParamsNextPage;
        if (this.currentPage < this.totalPages) {

            String pathNextPage = pathBase + "&page=" + (this.currentPage + 1) + "&pageSize=" + this.pageSize;
            baseUrlConstructor = host + pathNextPage;
            urlWithParamsNextPage = new StringBuilder(baseUrlConstructor);

            if (searchParams != null && !searchParams.isEmpty()) {
                urlWithParamsNextPage.append("?");
                searchParams.forEach((key, value) -> {
                    if (!Objects.equals(key, "page")) {
                        urlWithParamsNextPage.append(key).append("=").append(value).append("&");
                    }
                });

                urlWithParamsNextPage.deleteCharAt(urlWithParamsNextPage.length() - 1);
            }
        } else {
            urlWithParamsNextPage = null;
        }
        if (urlWithParamsNextPage == null) {
            res[0] = null;
        } else {
            res[0] = urlWithParamsNextPage.toString();
        }


        StringBuilder urlWithParamsPrevPagePage;
        if (this.currentPage > 1) {
            String pathNextPage = pathBase + "&page=" + (this.currentPage - 1) + "&pageSize=" + this.pageSize;


            baseUrlConstructor = host + pathNextPage;
            urlWithParamsPrevPagePage = new StringBuilder(baseUrlConstructor);

            if (searchParams != null && !searchParams.isEmpty()) {
                urlWithParamsPrevPagePage.append("?");
                searchParams.forEach((key, value) -> {
                    if (!Objects.equals(key, "page")) {
                        urlWithParamsPrevPagePage.append(key).append("=").append(value).append("&");
                    }

                });

                urlWithParamsPrevPagePage.deleteCharAt(urlWithParamsPrevPagePage.length() - 1);
            }
            res[1] = urlWithParamsPrevPagePage.toString();
        } else {
            res[1] = null;
        }

        return res;
    }

}


