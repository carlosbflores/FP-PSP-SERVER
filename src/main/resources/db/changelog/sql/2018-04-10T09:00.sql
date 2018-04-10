ALTER TABLE ps_families.person
  ADD COLUMN email character varying (255);

INSERT INTO data_collect.snapshots_properties_attributes(
  property_system_name, property_schema_name, stoplight_type, lang, title, stoplight_group)
  VALUES ('email', 'email', 'RECOMMENDED', 'es', 'Email', 'PERSONAL');
