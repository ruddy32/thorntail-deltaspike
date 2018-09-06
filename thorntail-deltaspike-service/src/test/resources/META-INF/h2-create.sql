
--
-- Role
--

create sequence sample.role_id_seq start with 1 increment by 1;

create table sample.role (
  id bigint not null,
  name varchar(64) not null,
  description varchar(256),
  bl_delete boolean not null,
  ts_create timestamp not null,
  ts_update timestamp not null,
  ts_delete timestamp,
  primary key (id)
);
alter table sample.role add constraint uk_role_name unique (name);
comment on table sample.role is 'Role';
comment on column sample.role.id is 'Role technical idenfier';
comment on column sample.role.name is 'Role name';
comment on column sample.role.description is 'Role description';
comment on column sample.role.bl_delete is 'Logical delete';
comment on column sample.role.ts_create is 'Creation date/time';
comment on column sample.role.ts_update is 'Last update date/time';
comment on column sample.role.ts_delete is 'Logical delete date/time';

--
-- User
--

create sequence sample.user_id_seq start with 1 increment by 1;

create table sample.user (
  id bigint not null,
  uid varchar(64) not null,
  name varchar(256) not null,
  bl_delete boolean not null,
  ts_create timestamp not null,
  ts_update timestamp not null,
  ts_delete timestamp,
  primary key (id)
);
alter table sample.user add constraint uk_user_uid unique (uid);
comment on table sample.user is 'User';
comment on column sample.user.id is 'User technical idenfier';
comment on column sample.user.uid is 'User identifier (e-mail)';
comment on column sample.user.name is 'Ful user name';
comment on column sample.user.bl_delete is 'Logical delete';
comment on column sample.user.ts_create is 'Creation date/time';
comment on column sample.user.ts_update is 'Last update date/time';
comment on column sample.user.ts_delete is 'Logical delete date/time';

--
-- Relations
--

create table sample.user_role_rel (
	user_id bigint not null,
	role_id bigint not null,
	primary key (user_id, role_id)
);
alter table sample.user_role_rel
	add constraint fk_user_role_rel_user_id foreign key (user_id) references sample.user;
alter table sample.user_role_rel
	add constraint fk_user_role_rel_role_id foreign key (role_id) references sample.role;