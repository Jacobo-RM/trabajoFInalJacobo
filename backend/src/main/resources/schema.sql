CREATE TABLE marcas (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    pais_origen VARCHAR(255),
    fecha_fundacion VARCHAR(10),
    sitio_web VARCHAR(255),
    descripcion VARCHAR(255),
    imagen_marca VARCHAR(255)

);

CREATE TABLE medicamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    descripcion VARCHAR(255),
    stock INT DEFAULT 0,
    marca_id INT,
    fecha_vencimiento VARCHAR(10),
    receta BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (marca_id) REFERENCES marcas(id)
);