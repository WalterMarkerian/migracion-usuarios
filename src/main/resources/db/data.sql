-- Insertar Sedes
INSERT INTO sede (id, nombre, direccion) VALUES (1, 'Sede Central', 'Av. Principal 123');
INSERT INTO sede (id, nombre, direccion) VALUES (2, 'Sede Norte', 'Calle Secundaria 456');

-- Insertar Usuarios en la DB1
INSERT INTO usuario (id, nombre, email, dni, sede_id) VALUES (1, 'Juan Pérez', 'juan@email.com', '12345678', 1);
INSERT INTO usuario (id, nombre, email, dni, sede_id) VALUES (2, 'Ana Gómez', 'ana@email.com', '87654321', 2);
INSERT INTO usuario (id, nombre, email, dni, sede_id) VALUES (3, 'Carlos Ramírez', 'carlos@email.com', '11223344', 1);
