package Database;

import domain.GameInfo;

public interface IDataStorageAdapter {
    void save(String key, Object value);
    GameInfo load(String key);
}
