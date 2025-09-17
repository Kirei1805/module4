package loipt.example.homdientu.repository;

import loipt.example.homdientu.model.EmailSettings;

public interface ISettingsRepository {
    EmailSettings getSettings();
    void saveSettings(EmailSettings settings);
}
