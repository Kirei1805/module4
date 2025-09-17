package loipt.example.homdientu.model;


public class EmailSettings {
    private String language;
    private Integer pageSize;
    private boolean spamFilter;
    private String signature;

    public EmailSettings() {
    }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }

    public boolean isSpamFilter() { return spamFilter; }
    public void setSpamFilter(boolean spamFilter) { this.spamFilter = spamFilter; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
}
