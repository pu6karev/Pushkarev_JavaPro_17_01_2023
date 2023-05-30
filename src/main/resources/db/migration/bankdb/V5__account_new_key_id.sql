-- Добавить ограничение уникальности на столбец uid в таблице persons
ALTER TABLE persons ADD CONSTRAINT uid_unique UNIQUE (uid);

-- Удалить текущее ограничение внешнего ключа
ALTER TABLE accounts DROP CONSTRAINT fk_person_id;

-- Добавить новое ограничение внешнего ключа на столбец uid
ALTER TABLE accounts ADD CONSTRAINT fk_uid FOREIGN KEY (uid) REFERENCES persons (uid);