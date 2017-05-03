-- Create table contacts
CREATE TABLE contacts
(
  id         SERIAL                 NOT NULL,
  first_name CHARACTER VARYING(255) NOT NULL,
  last_name  CHARACTER VARYING(255) NOT NULL,
  birth_date TIMESTAMP,

  CONSTRAINT pk__contacts PRIMARY KEY (id),

  CONSTRAINT uq__contacts_name UNIQUE (first_name, last_name)
);

COMMENT ON TABLE contacts IS 'Phone Book Contacts';
COMMENT ON COLUMN contacts.id IS 'Unique identifier';
COMMENT ON COLUMN contacts.first_name IS 'First Name';
COMMENT ON COLUMN contacts.last_name IS 'Last Name';
COMMENT ON COLUMN contacts.birth_date IS 'Date of birth';

-- Create table contacts_details
CREATE TABLE contacts_details
(
  id           SERIAL                 NOT NULL,
  contact_id   INT                    NOT NULL,
  phone_type   CHARACTER VARYING(255) NOT NULL,
  phone_number CHARACTER VARYING(255) NOT NULL,

  CONSTRAINT pk__contacts_details PRIMARY KEY (id),

  CONSTRAINT uq__contacts_name UNIQUE (contact_id, phone_number),

  CONSTRAINT fk__contacts_details_contacts FOREIGN KEY (id)
  REFERENCES contacts (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

COMMENT ON TABLE contacts_details IS 'Phone Book Contacts Details';
COMMENT ON COLUMN contacts_details.id IS 'Unique identifier';
COMMENT ON COLUMN contacts_details.contact_id IS 'Contact ID';
COMMENT ON COLUMN contacts_details.phone_type IS 'Phone Type';
COMMENT ON COLUMN contacts_details.phone_number IS 'Phone Number';