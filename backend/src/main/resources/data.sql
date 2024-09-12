INSERT INTO marcas (nombre, pais_origen, fecha_fundacion, sitio_web, descripcion, imagen_marca) VALUES 
('Pfizer', 'Estados Unidos', '1849-10-22', 'https://www.pfizer.com', 'Compañía farmacéutica multinacional', '/images/pfizer.png'),
('Novartis', 'Suiza', '1996-03-07', 'https://www.novartis.com', 'Líder en investigación y desarrollo de medicamentos', '/images/novartis.png'),
('Bayer', 'Alemania', '1863-08-01', 'https://www.bayer.com', 'Especializada en productos farmacéuticos y agrícolas', '/images/bayer.png'),
('Johnson & Johnson', 'Estados Unidos', '1886-01-01', 'https://www.jnj.com', 'Multinacional dedicada a la fabricación de productos farmacéuticos y médicos', '/images/jnj.png');


INSERT INTO medicamentos (nombre, precio, descripcion, stock, marca_id, fecha_vencimiento, receta) VALUES 
('Aspirina', 4.99, 'Analgésico y antiinflamatorio', 100, 3, '2025-12-31', FALSE),
('Lipitor', 29.99, 'Medicamento para reducir el colesterol', 50, 1, '2024-10-01', TRUE),
('Diovan', 19.99, 'Medicamento para la hipertensión', 75, 2, '2026-03-15', TRUE),
('Tylenol', 5.99, 'Analgésico y antipirético', 120, 4, '2025-06-15', FALSE);




