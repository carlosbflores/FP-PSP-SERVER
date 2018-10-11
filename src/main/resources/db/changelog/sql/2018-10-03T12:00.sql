CREATE TABLE ps_network.sub_organizations (

    id bigserial NOT NULL,
    description character varying(50),
    created_date date NOT NULL,
    organization_id bigint NOT NULL,
    sub_organization_id bigint NOT NULL,
    application_id bigint NOT NULL,
    constraint  sub_organizations_pkey PRIMARY KEY (id),
    CONSTRAINT fk_organization_id_01 FOREIGN KEY (organization_id) REFERENCES ps_network.organizations (id),
    CONSTRAINT fk_application_id FOREIGN KEY (application_id) REFERENCES ps_network.applications (id)
);

--CREATE SEQUENCE ps_network.groups_organizations_id_seq;
ALTER TABLE ps_network.sub_organizations ALTER COLUMN id SET DEFAULT nextval('ps_network.sub_organizations_id_seq');



