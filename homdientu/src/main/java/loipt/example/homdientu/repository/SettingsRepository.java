package loipt.example.homdientu.repository;

import loipt.example.homdientu.model.EmailSettings;
import org.springframework.stereotype.Repository;

@Repository
public class SettingsRepository implements ISettingsRepository {
    private EmailSettings settings;

    public SettingsRepository() {
        settings = new EmailSettings();
        settings.setLanguage("English");
        settings.setPageSize(25);
        settings.setSpamFilter(true);
        settings.setSignature("Thor\nKing, Asgard");
    }

    @Override
    public EmailSettings getSettings() {
        return settings;
    }

    @Override
    public void saveSettings(EmailSettings s) {
        this.settings = s;
    }
}
