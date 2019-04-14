truncate table resources_shadowfax;
truncate table resources_lightfax;

select cpu_usage from resources_shadowfax order by idresources desc limit 1,1;

select cpu_usage from resources_shadowfax where idresources=(SELECT max(idresources)-1 from resources_shadowfax);

delete from resources_shadowfax where idresources>2;
delete from resources_lightfax where idresources>2;
delete from resources_darkfax where idresources>2;
delete from resources_brightfax where idresources>2;


ALTER TABLE resources_shadowfax AUTO_INCREMENT = 2;
ALTER TABLE resources_lightfax AUTO_INCREMENT = 2;
ALTER TABLE resources_brightfax AUTO_INCREMENT = 2;
ALTER TABLE resources_darkfax AUTO_INCREMENT = 2;

SELECT table_schema "vault",
        ROUND(SUM(data_length + index_length) / 1024 , 1) "DB Size in KB" 
FROM information_schema.tables 
GROUP BY table_schema; 

select processes from
  (select processes from resources_shadowfax order by idresources desc limit 2) resources_shadowfax
order by idresources limit 1;

SELECT processes FROM resources_shadowfax ORDER BY idresources DESC LIMIT 0,1;

SELECT * FROM resources_shadowfax ORDER BY idresources DESC LIMIT 552,1;