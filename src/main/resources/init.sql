truncate table trips cascade ;
truncate table seats cascade ;
truncate table vehicles cascade ;

-- Vehícle inserts
INSERT INTO vehicles (id,model, brand, year, Type)
VALUES (1,'Scania Touring', 'Scania', 2020, 'Bus'),
       (2,'Volvo 9700', 'Volvo', 2021, 'Bus'),
       (3,'Mercedes-Benz Travego', 'Mercedes-Benz', 2019, 'Bus'),
       (4,'Man Lion Coach', 'MAN', 2020, 'Bus'),
       (5,'Irizar i8', 'Irizar', 2022, 'Bus'),
       (6,'Volvo B11R', 'Volvo', 2021, 'Bus'),
       (7,'Marcopolo Paradiso 1200', 'Marcopolo', 2020, 'Bus'),
       (8,'King Long XMQ6129Y', 'King Long', 2021, 'Bus'),
       (9,'Yutong ZK6129H', 'Yutong', 2019, 'Bus'),
       (10,'Golden Dragon XML6125', 'Golden Dragon', 2022, 'Bus');

-- Seats inserts
DO
$$
    DECLARE
        vehicle_id  INT;
        seat_number INT;
    BEGIN
        FOR vehicle_id IN 1..10
            LOOP
                FOR seat_number IN 1..20
                    LOOP
                        INSERT INTO seats (number, block_time, vehicle_id)
                        VALUES (seat_number, NULL, vehicle_id);
                    END LOOP;
            END LOOP;
    END
$$;

-- Tripns insert
insert into trips (arrival_time, created_on, departure_time, destination, price, updated_on, description, image_url, vehicle_id)
values (NOW() + INTERVAL '14 days', NOW(), NOW() + INTERVAL '12 days', 'Cusco', 150, NOW(),
        'Conoce las maravillas del Cusco con este increíble tour',
        'https://www.chullostravelperu.com/wp-content/uploads/2023/08/Plaza-de-armas-del-cusco.jpg',1),
       (NOW() + INTERVAL '16 days', NOW(), NOW() + INTERVAL '14 days', 'Lima', 120, NOW(),
        'Descubre la historia y gastronomía de Lima en este tour especial',
        'https://larepublica.cronosmedia.glr.pe/migration/images/I5BASDVHG5BI3OCYS7VSNG2ZXY.jpg',2),
       (NOW() + INTERVAL '20 days', NOW(), NOW() + INTERVAL '18 days', 'Arequipa', 180, NOW(),
        'Explora la ciudad blanca de Arequipa y sus paisajes únicos',
        'https://www.tangol.com/Blog/Fotos/arequipa-the-white-city-on-peru_0_201804180914180-resized.jpg',3),
       (NOW() + INTERVAL '10 days', NOW(), NOW() + INTERVAL '8 days', 'Puno', 130, NOW(),
        'Disfruta del Lago Titicaca y sus islas en este tour memorable',
        'https://terandes.com/wp-content/uploads/2022/10/Lago-Titicaca-1-900x600.webp', 4),
       (NOW() + INTERVAL '25 days', NOW(), NOW() + INTERVAL '23 days', 'Cusco', 200, NOW(),
        'Vive la experiencia única de Machu Picchu con todo incluido',
        'https://images.unsplash.com/photo-1526392060635-9d6019884377?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80', 5),
       (NOW() + INTERVAL '30 days', NOW(), NOW() + INTERVAL '28 days', 'Iquitos', 250, NOW(),
        'Explora la selva amazónica y la biodiversidad de Iquitos',
        'https://orangenationperu.com/wp-content/uploads/2023/06/travel-guide-to-iquitos-in-peru.jpg',6),
       (NOW() + INTERVAL '15 days', NOW(), NOW() + INTERVAL '13 days', 'Trujillo', 110, NOW(),
        'Descubre las huacas del sol y la luna en este viaje cultural',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXXkjD7EN4SXYCS4ldMgTocP4mrxu9_nPluw&s', 7),
       (NOW() + INTERVAL '22 days', NOW(), NOW() + INTERVAL '20 days', 'Huaraz', 170, NOW(),
        'Admira los paisajes andinos y glaciares en este tour en Huaraz',
        'https://cdn.www.gob.pe/uploads/document/file/938763/standard_ANA_1.jpg',8),
       (NOW() + INTERVAL '18 days', NOW(), NOW() + INTERVAL '16 days', 'Tacna', 90, NOW(),
        'Conoce la rica historia y cultura de Tacna en este viaje',
        'https://www.infobae.com/resizer/v2/LRPPDLUQU5EJNCVVRP4TGIAPYA.jpg?auth=ba810b10213b970ba54e4684a3c8648b4a7c7a3ac7acab85750ec310d6e05064&smart=true&width=1024&height=512&quality=85',9),
       (NOW() + INTERVAL '12 days', NOW(), NOW() + INTERVAL '10 days', 'Chiclayo', 140, NOW(),
        'Explora la cultura Mochica y la ruta del Señor de Sipán en Chiclayo',
        'https://portal.andina.pe/EDPfotografia3/Thumbnail/2023/11/07/001010523W.jpg',10);


