INSERT INTO marcas (nombre, pais_origen, fecha_fundacion, sitio_web, descripcion, imagen_marca) VALUES 
('Pfizer', 'Estados Unidos', '1849-10-22', 'https://www.pfizer.com', 'Compañía farmacéutica multinacional', '/images/pfizer.png'),
('Novartis', 'Suiza', '1996-03-07', 'https://www.novartis.com', 'Líder en investigación y desarrollo de medicamentos', '/images/novartis.png'),
('Bayer', 'Alemania', '1863-08-01', 'https://www.bayer.com', 'Especializada en productos farmacéuticos y agrícolas', '/images/bayer.png'),
('Johnson & Johnson', 'Estados Unidos', '1886-01-01', 'https://www.jnj.com', 'Multinacional dedicada a la fabricación de productos farmacéuticos y médicos', '/images/jnj.png');

INSERT INTO medicamentos (nombre, precio, descripcion, stock, marca_id, fecha_vencimiento, receta) VALUES 
('Aspirina', 4.99, 'Analgésico y antiinflamatorio', 100, 3, '2025-12-31', FALSE),
('Lipitor', 29.99, 'Medicamento para reducir el colesterol', 50, 1, '2024-10-01', TRUE),
('Diovan', 19.99, 'Medicamento para la hipertensión', 75, 2, '2026-03-15', TRUE),
('Tylenol', 5.99, 'Analgésico y antipirético', 120, 4, '2025-06-15', FALSE),
('Zithromax', 15.99, 'Antibiótico para infecciones bacterianas', 40, 1, '2025-09-21', TRUE),
('Celebrex', 13.99, 'Tratamiento para la artritis y el dolor', 80, 1, '2024-12-31', TRUE),
('Voltaren', 7.49, 'Gel para alivio del dolor muscular', 200, 2, '2025-03-30', FALSE),
('Aleve', 3.99, 'Analgésico de venta libre para el dolor', 150, 3, '2026-01-01', FALSE),
('Claritin', 11.99, 'Antihistamínico para la alergia', 90, 4, '2024-08-10', FALSE),
('Xarelto', 45.99, 'Anticoagulante para prevenir coágulos sanguíneos', 30, 2, '2025-11-05', TRUE),
('Icy Hot', 8.99, 'Ungüento para alivio del dolor muscular', 60, 3, '2024-06-18', FALSE),
('Imodium', 6.99, 'Tratamiento para la diarrea', 130, 4, '2026-09-30', FALSE),
('Prevacid', 14.99, 'Tratamiento para el reflujo ácido y úlceras', 70, 2, '2025-07-20', TRUE),
('Mylanta', 5.49, 'Antiácido para el alivio de la acidez estomacal', 160, 1, '2025-04-15', FALSE);
