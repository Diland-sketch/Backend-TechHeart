
-- Crear roles
INSERT INTO roles (id_rol, nombre_rol) VALUES 
(1, 'ADMIN'),
(2, 'MEDICO'),
(3, 'PACIENTE');

-- Crear usuario administrador
INSERT INTO usuarios (
    identificacion, nombre_usuario, contraseña, email, id_rol,
    primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,
    edad, sexo, fecha_registro
) VALUES (
    '10000001', 'admin', 'admin123', 'admin@techheart.com', 1,
    'Ana', NULL, 'González', NULL,
    40, 'F', CURRENT_TIMESTAMP
);

-- Crear médico
INSERT INTO usuarios (
    identificacion, nombre_usuario, contraseña, email, id_rol,
    primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,
    edad, sexo, fecha_registro
) VALUES (
    '20000002', 'medico1', 'medico123', 'carlos@techheart.com', 2,
    'Carlos', NULL, 'Rojas', NULL,
    35, 'M', CURRENT_TIMESTAMP
);

INSERT INTO medico (
    identificacion, especialidad, fecha_graduacion, lugar_graduacion
) VALUES (
    '20000002', 'Cardiología', '2015-12-10', 'UNAL'
);

-- Crear paciente
INSERT INTO usuarios (
    identificacion, nombre_usuario, contraseña, email, id_rol,
    primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,
    edad, sexo, fecha_registro
) VALUES (
    '30000003', 'paciente1', 'paciente123', 'laura@techheart.com', 3,
    'Laura', NULL, 'Martínez', NULL,
    28, 'F', CURRENT_TIMESTAMP
);

INSERT INTO paciente (
    identificacion, tipo_sangre, condiciones_medicas, telefono
) VALUES (
    '30000003', 'O+', 'Asma', 3100000000
);
