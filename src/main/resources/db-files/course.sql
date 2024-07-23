--------------------------------------------------------
--  DDL for Table COURSE
--------------------------------------------------------

  CREATE TABLE "ABDALLAH"."COURSE"
   (	"ID" NUMBER,
	"NAME" VARCHAR2(255 BYTE),
	"DESCRIPTION" VARCHAR2(255 BYTE),
	"RATING" NUMBER DEFAULT 0,
	"INSTRUCTOR_NAME" VARCHAR2(255 BYTE),
	"START_DATE" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE" ;
--------------------------------------------------------
--  DDL for Index COURSE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ABDALLAH"."COURSE_PK" ON "ABDALLAH"."COURSE" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE" ;
--------------------------------------------------------
--  DDL for Trigger COURSE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ABDALLAH"."COURSE_TRIGGER"
BEFORE INSERT ON course
FOR EACH ROW

BEGIN
  SELECT course_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

/
ALTER TRIGGER "ABDALLAH"."COURSE_TRIGGER" ENABLE;
--------------------------------------------------------
--  Constraints for Table COURSE
--------------------------------------------------------

  ALTER TABLE "ABDALLAH"."COURSE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."COURSE" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."COURSE" MODIFY ("RATING" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."COURSE" MODIFY ("INSTRUCTOR_NAME" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."COURSE" MODIFY ("START_DATE" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."COURSE" ADD CONSTRAINT "COURSE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE"  ENABLE;