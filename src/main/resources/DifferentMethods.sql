select avg(height) from heroinfo;
-- имя героя с самым большим ростом
select name, height  from heroinfo where height = (select max(height) from heroinfo);
select name, height  from heroinfo order by height desc  limit 1;
-- имя  самого тяжелого героя
select name, weight  from heroinfo order by weight desc limit 1;
-- Кількість осіб в кожній гендерній групі
select gender, count(gender) from heroinfo group by gender;
-- Кількість осіб в кожному угрупуванні (добро / зло / інші)
select alignment, count(alignment) from heroinfo group by alignment;
-- 5 назв самих популярних видавців
select publisher, count(publisher) from heroinfo group by publisher order by count desc limit 5;
-- 3 назви найрозповсюдженіших кольорів волосся
select hair_color, count(hair_color) from heroinfo where hair_color not like '%-%' group by hair_color order by count desc limit 3 ;
-- Найрозповсюдженіший колір очей
select eye_color, count(eye_color) from heroinfo where eye_color not like '%-%' group by eye_color order by count desc limit 1 ;
