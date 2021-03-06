--liquibase formatted sql

--
-- Role
--

--changeset fernandosm:1
CREATE SEQUENCE IF NOT EXISTS sample.role_id_seq;
--rollback DROP SEQUENCE sample.role_id_seq;
--changeset fernandosm:2
-- ALTER SEQUENCE sample.role_id_seq OWNER TO 'test';
--rollback not required;

--changeset fernandosm:3
CREATE TABLE IF NOT EXISTS sample.role (
  id bigint NOT NULL default nextval('sample.role_id_seq') PRIMARY KEY,
  name varchar(64) UNIQUE NOT NULL,
  description varchar(256),
  bl_delete boolean NOT NULL default false,
  ts_create timestamp NOT NULL default (now() at time zone 'utc'),
  ts_update timestamp NOT NULL default (now() at time zone 'utc'),
  ts_delete timestamp default null
);
COMMENT ON TABLE sample.role IS 'Role';
COMMENT ON COLUMN sample.role.id IS 'Role technical idenfier';
COMMENT ON COLUMN sample.role.name IS 'Role name';
COMMENT ON COLUMN sample.role.description IS 'Role description';
COMMENT ON COLUMN sample.role.bl_delete IS 'Logical delete';
COMMENT ON COLUMN sample.role.ts_create IS 'Creation date/time';
COMMENT ON COLUMN sample.role.ts_update IS 'Last update date/time';
COMMENT ON COLUMN sample.role.ts_delete IS 'Logical delete date/time';
--rollback DROP TABLE sample.role;
--changeset fernandosm:4
-- ALTER TABLE sample.role OWNER TO 'test';
--rollback not required;

--
-- User
--

--changeset fernandosm:5
CREATE SEQUENCE IF NOT EXISTS sample.user_id_seq;
--rollback DROP SEQUENCE sample.user_id_seq;
--changeset fernandosm:6
-- ALTER SEQUENCE sample.user_id_seq OWNER TO 'test';
--rollback not required;

--changeset fernandosm:7
CREATE TABLE IF NOT EXISTS sample.user (
  id bigint NOT NULL default nextval('sample.user_id_seq') PRIMARY KEY,
  uid varchar(64) UNIQUE NOT NULL,
  name varchar(256) NOT NULL,
  bl_delete boolean NOT NULL default false,
  ts_create timestamp NOT NULL default (now() at time zone 'utc'),
  ts_update timestamp NOT NULL default (now() at time zone 'utc'),
  ts_delete timestamp default null
);
COMMENT ON TABLE sample.user IS 'User';
COMMENT ON COLUMN sample.user.id IS 'User technical idenfier';
COMMENT ON COLUMN sample.user.uid IS 'User identifier (e-mail)';
COMMENT ON COLUMN sample.user.name IS 'Ful user name';
COMMENT ON COLUMN sample.user.bl_delete IS 'Logical delete';
COMMENT ON COLUMN sample.user.ts_create IS 'Creation date/time';
COMMENT ON COLUMN sample.user.ts_update IS 'Last update date/time';
COMMENT ON COLUMN sample.user.ts_delete IS 'Logical delete date/time';
--rollback DROP TABLE sample.user;
--changeset fernandosm:8
-- ALTER TABLE sample.user OWNER TO 'test';
--rollback not required;