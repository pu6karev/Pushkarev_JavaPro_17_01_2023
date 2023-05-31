-- Удалить текущее ограничение внешнего ключа
ALTER TABLE accounts DROP CONSTRAINT fk_uid;

-- Добавить новое ограничение внешнего ключа на столбец id
ALTER TABLE accounts ADD CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES persons (id);