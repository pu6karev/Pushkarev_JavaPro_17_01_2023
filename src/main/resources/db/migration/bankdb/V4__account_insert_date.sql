ALTER TABLE accounts ADD COLUMN IF NOT EXISTS created_at TIMESTAMP;
ALTER TABLE accounts ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;