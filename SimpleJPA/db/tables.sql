/* ----------------------- DROP EXISTING / DO NOT USE IN LIVE PRODUCTION SITE -------------------- */
use SimpleJPA;
drop table if exists tasks;

CREATE TABLE tasks (
  id bigint  UNSIGNED NOT NULL AUTO_INCREMENT,  /* Maps to Java "long" with no @Column annotation required */
  title VARCHAR(250),
  description text,  
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


