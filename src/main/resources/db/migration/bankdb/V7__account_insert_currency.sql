ALTER TABLE accounts ADD COLUMN IF NOT EXISTS currency VARCHAR(3);

UPDATE accounts SET currency = 'UAH';

ALTER TABLE accounts ADD CONSTRAINT currency_not_null CHECK (currency IS NOT NULL);