--
-- This file contains the out of box menus items (modules and sections) for the admin and their corresponding
-- permission mappings.
--
-- Client systems can choose new modules and associated permission mappings by deleting rows they don't want and adding
-- others that make sense for their admin.
--
-- Broadleaf Commerce add-on modules will add rows to these tables as needed.
--

--
-- Create BLC MODULES (These modules are required for the admin left navigation)
--
INSERT INTO BLC_ADMIN_MODULE (ADMIN_MODULE_ID, NAME, MODULE_KEY, ICON, DISPLAY_ORDER) VALUES (-6,'App','AppConfig', 'icon-barcode', 6);


--
-- Create BLC SECTIONS (These modules are required for the admin left navigation)
--
INSERT INTO BLC_ADMIN_SECTION (ADMIN_SECTION_ID, DISPLAY_ORDER, ADMIN_MODULE_ID, NAME, SECTION_KEY, URL, USE_DEFAULT_HANDLER, CEILING_ENTITY) VALUES (-14, 1000, -6, 'Cell', 'Cell', '/cell', FALSE, 'com.appfactory.domain.Cell');

INSERT INTO BLC_ADMIN_PERMISSION (ADMIN_PERMISSION_ID, DESCRIPTION, NAME, PERMISSION_TYPE) VALUES (-99,'ALL CELLS','PERMISSION_ALL_CELL', 'ALL');
INSERT INTO BLC_ADMIN_PERMISSION_ENTITY (ADMIN_PERMISSION_ENTITY_ID, CEILING_ENTITY, ADMIN_PERMISSION_ID) VALUES (-99, 'com.appfactory.domain.Cell', -99);


INSERT INTO BLC_ADMIN_ROLE_PERMISSION_XREF(ADMIN_ROLE_ID, ADMIN_PERMISSION_ID) VALUES(-1, -99);

INSERT INTO BLC_ADMIN_SEC_PERM_XREF(ADMIN_SECTION_ID, ADMIN_PERMISSION_ID) VALUES(-14, -99);