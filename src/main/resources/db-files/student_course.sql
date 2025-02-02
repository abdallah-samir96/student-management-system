--------------------------------------------------------
--  DDL for Table STUDENT_COURSE
--------------------------------------------------------

  CREATE TABLE "ABDALLAH"."STUDENT_COURSE"
   (	"STUDENT_ID" NUMBER,
	"COURSE_ID" NUMBER,
	"ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE" ;
--------------------------------------------------------
--  DDL for Index SYS_C007483
--------------------------------------------------------

  CREATE UNIQUE INDEX "ABDALLAH"."SYS_C007483" ON "ABDALLAH"."STUDENT_COURSE" ("STUDENT_ID", "COURSE_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE" ;
--------------------------------------------------------
--  DDL for Trigger STUDENT_COURSE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ABDALLAH"."STUDENT_COURSE_TRIGGER"
BEFORE INSERT ON student_course
FOR EACH ROW

BEGIN
  SELECT student_course_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

/
ALTER TRIGGER "ABDALLAH"."STUDENT_COURSE_TRIGGER" ENABLE;
--------------------------------------------------------
--  Constraints for Table STUDENT_COURSE
--------------------------------------------------------

  ALTER TABLE "ABDALLAH"."STUDENT_COURSE" ADD PRIMARY KEY ("STUDENT_ID", "COURSE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table STUDENT_COURSE
--------------------------------------------------------

  ALTER TABLE "ABDALLAH"."STUDENT_COURSE" ADD FOREIGN KEY ("STUDENT_ID")
	  REFERENCES "ABDALLAH"."STUDENT" ("ID") ENABLE;
  ALTER TABLE "ABDALLAH"."STUDENT_COURSE" ADD FOREIGN KEY ("COURSE_ID")
	  REFERENCES "ABDALLAH"."COURSE" ("ID") ENABLE;
