-- создаем копию таблицы с именем HEROES
CREATE TABLE public.heroes (
	id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	"name" varchar(255) NOT NULL,
	gender varchar(10) NULL,
	eye_color varchar(25) NULL,
	race varchar(50) NULL,
	hair_color varchar(25) NULL,
	height float8 NULL,
	publisher varchar(100) NULL,
	skin_color varchar(25) NULL,
	alignment varchar(10) NULL,
	weight int4 NULL,
	CONSTRAINT heroes_pk PRIMARY KEY (id)
);

-- перенос данных
insert into heroes(name, gender, eye_color, race, hair_color, height, publisher, skin_color, alignment, weight)
select name, gender, eye_color, race, hair_color, height, publisher, skin_color, alignment, weight
from heroinfo;

-- мігрувати в неї всіх видавців (лише унікальні) з таблиці heroes
insert into publishers (name) select distinct publisher from heroes;

-- додати в heroes поле publisher_id (foreign key на id в publishers)
alter table heroes add column publisher_id INTEGER;
update heroes h set publisher_id = p.id from publishers p where h.publisher = p.name;
alter table heroes add constraint fk_publisher foreign key (publisher_id) references publishers (id);

-- видалити колонку publisher з героїв
alter table heroes drop column publisher;

-- 5 назв самих популярних видавців
select p.name, COUNT(p.name) from heroes h join publishers p on h.publisher_id = p.id
       group by p.name order by count desc limit 5;