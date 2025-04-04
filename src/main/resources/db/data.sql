-- Limpiar tablas (opcional, solo si quieres reiniciar los datos)
DELETE FROM usuarios;
DELETE FROM sedes;

-- Insertar sedes
INSERT INTO sedes (id, nombre, direccion, ciudad) VALUES
(1, 'Sede Central', 'Av. Corrientes 1234', 'Buenos Aires'),
(2, 'Sede Norte', 'Av. Santa Fe 567', 'Rosario'),
(3, 'Sede Sur', 'Calle Rivadavia 890', 'La Plata'),
(4, 'Sede Oeste', 'Av. San Martín 321', 'Mendoza'),
(5, 'Sede Este', 'Av. Colón 654', 'Mar del Plata');

-- Insertar usuarios
INSERT INTO usuarios (id, nombre, apellido, email, telefono, dni, sede_id, estado) VALUES
(1, 'Juan', 'Pérez', 'juan.perez@sportclub.com', '1122334455', 38618902, 1, 'AUTORIZADO'),
(2, 'María', 'Gómez', 'maria.gomez@sportclub.com', '1144556677', 28765432, 1, 'AUTORIZADO'),
(3, 'Carlos', 'López', 'carlos.lopez@sportclub.com', '1155667788', 32987654, 2, 'AUTORIZADO'),
(4, 'Ana', 'Rodríguez', 'ana.rodriguez@sportclub.com', '1166778899', 34567890, 3, 'DENEGADO'),
(5, 'Luis', 'Martínez', 'luis.martinez@sportclub.com', '1177889900', 31234567, 4, 'AUTORIZADO'),
(6, 'Sofía', 'Fernández', 'sofia.fernandez@sportclub.com', '1188990011', 35678901, 5, 'AUTORIZADO'),
(7, 'Pedro', 'Díaz', 'pedro.diaz@sportclub.com', '1199001122', 33445566, 2, 'AUTORIZADO'),
(8, 'Laura', 'Sánchez', 'laura.sanchez@sportclub.com', '1100112233', 37890123, 3, 'DENEGADO'),
(9, 'Diego', 'González', 'diego.gonzalez@sportclub.com', '1111223344', 39012345, 4, 'AUTORIZADO'),
(10, 'Valeria', 'Torres', 'valeria.torres@sportclub.com', '1122334400', 30123456, 5, 'AUTORIZADO');

-- Reiniciar secuencias (para evitar conflictos con IDs)
ALTER SEQUENCE IF EXISTS sedes_id_seq RESTART WITH 6;
ALTER SEQUENCE IF EXISTS usuarios_id_seq RESTART WITH 11;