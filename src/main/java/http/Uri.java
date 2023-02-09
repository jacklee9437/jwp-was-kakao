package http;

import http.request.RequestParams;

import java.util.Optional;

public class Uri {
    private final String uri;
    private String path;
    private final Optional<String> extension;
    private final Optional<RequestParams> params;

    public Uri(String uri) {
        this.uri = this.path = uri;
        this.params = extractParams();
        this.extension = extractExtension();
    }

    public String getUri() {
        return uri;
    }

    public Optional<String> getExtension() {
        return extension;
    }

    public Optional<RequestParams> getParams() {
        return params;
    }

    public String getPath() {
        return path;
    }

    private Optional<RequestParams> extractParams() {
        String[] splittedUri = uri.split("\\?", 2);
        if (!isSplitted(splittedUri)) {
            return Optional.empty();
        }
        this.path = splittedUri[0];
        String queryString = splittedUri[1];
        return Optional.of(RequestParams.fromQueryString(queryString));
    }

    private Optional<String> extractExtension() {
        String[] splittedUri = path.split("\\.");
        if (!isSplitted(splittedUri)) {
            return Optional.empty();
        }
        String extention = splittedUri[splittedUri.length - 1];
        if (!ContentType.isFileExtension(extention)) {
            return Optional.empty();
        }
        return Optional.of(extention);
    }

    private boolean isSplitted(String[] splittedUri) {
        return splittedUri.length > 1;
    }
}
