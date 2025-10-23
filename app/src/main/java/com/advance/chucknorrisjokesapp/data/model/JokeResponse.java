package com.advance.chucknorrisjokesapp.data.model;

import java.util.List;

public class JokeResponse {
    private int total;
    private List<JokeDto> result;

    public List<JokeDto> getResult() {
        return result;
    }

    public int getTotal() {
        return total;
    }

    public static class JokeDto {
        private String id;
        private String value;
        private String icon_url;

        public JokeDto(String id, String value, String icon_url) {
            this.id = id;
            this.value = value;
            this.icon_url = icon_url;
        }

        public String getId() {
            return id;
        }

        public String getValue() {
            return value;
        }

        public String getIconUrl() {
            return icon_url;
        }
    }
}

