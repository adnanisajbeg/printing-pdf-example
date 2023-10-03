CREATE TABLE prints
(
    id uuid PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    file_name varchar(255) NOT NULL,
    printer_name varchar(255) NOT NULL,
    file_size bigint NOT NULL,
    printed_at timestamp with time zone NOT NULL DEFAULT now(),
    file_data bytea NOT NULL,
    status varchar(255) NOT NULL
);