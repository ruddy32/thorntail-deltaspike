
--
-- Relations
--
drop table sample.user_role_rel if exists;

--
-- User
--

drop table sample.user if exists;
drop sequence if exists sample.user_id_seq;

--
-- Role
--

drop table sample.role if exists;
drop sequence if exists sample.role_id_seq;