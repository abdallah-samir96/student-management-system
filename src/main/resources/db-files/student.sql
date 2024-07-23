--------------------------------------------------------
--  DDL for Table STUDENT
--------------------------------------------------------

  CREATE TABLE "ABDALLAH"."STUDENT"
   (	"ID" NUMBER,
	"FIRST_NAME" VARCHAR2(255 BYTE),
	"LAST_NAME" VARCHAR2(255 BYTE),
	"EMAIL" VARCHAR2(255 BYTE),
	"BIRTH_DATE" TIMESTAMP (6) DEFAULT SYSTIMESTAMP
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE" ;
--------------------------------------------------------
--  DDL for Index STUDENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ABDALLAH"."STUDENT_PK" ON "ABDALLAH"."STUDENT" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE" ;
--------------------------------------------------------
--  DDL for Trigger STUDENT_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "ABDALLAH"."STUDENT_TRIGGER"
BEFORE INSERT ON student
FOR EACH ROW

BEGIN
  SELECT student_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;

/
ALTER TRIGGER "ABDALLAH"."STUDENT_TRIGGER" ENABLE;
--------------------------------------------------------
--  Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "ABDALLAH"."STUDENT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."STUDENT" MODIFY ("FIRST_NAME" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."STUDENT" MODIFY ("LAST_NAME" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."STUDENT" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "ABDALLAH"."STUDENT" ADD CONSTRAINT "STUDENT_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "ABDALLAH_TABSPACEE"  ENABLE;
