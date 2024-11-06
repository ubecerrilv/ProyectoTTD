--Cambiar usuario
CONNECT gesob_user/UsuarioTTD24$@xepdb1

-- Creación de la tabla obra
CREATE TABLE obra (
                      id           INTEGER NOT NULL,
                      nombre       VARCHAR2(100) NOT NULL,
                      ubicacion    VARCHAR2(200) NOT NULL,
                      presupuesto  NUMBER(11, 2) NOT NULL,
                      fecha_inicio DATE NOT NULL,
                      fecha_fin    DATE NOT NULL
);

ALTER TABLE obra ADD CONSTRAINT obra_pk PRIMARY KEY ( id );

-- Creación de la tabla trabajador
CREATE TABLE trabajador (
                            id         INTEGER NOT NULL,
                            nombre     VARCHAR2(50) NOT NULL,
                            apellido_1 VARCHAR2(50) NOT NULL,
                            apellido_2 VARCHAR2(50) NOT NULL,
                            edad       INTEGER NOT NULL,
                            tipo       VARCHAR2(20) NOT NULL,
                            estado     NUMBER NOT NULL,
                            obra_id    INTEGER
);

ALTER TABLE trabajador ADD CONSTRAINT trabajador_pk PRIMARY KEY ( id );

-- Clave foránea para asociar trabajador con obra
ALTER TABLE trabajador
    ADD CONSTRAINT trabajador_obra_fk FOREIGN KEY ( obra_id )
        REFERENCES obra ( id );

-- Secuencia y para autoincremento del ID en la tabla obra
CREATE SEQUENCE obra_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Secuencia y para autoincremento del ID en la tabla trabajador
CREATE SEQUENCE trabajador_id_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

--Trigger para desvincular el trabajador de una obra
CREATE OR REPLACE TRIGGER actualizar_trabajadores_fecha_fin
AFTER UPDATE OR INSERT ON obra
    FOR EACH ROW
BEGIN
    -- Comprobar si la fecha de fin ha sido alcanzada o superada
    IF :NEW.fecha_fin <= SYSDATE THEN
        -- Actualizar obra_id a NULL para los trabajadores de la obra
UPDATE trabajador
SET obra_id = NULL
WHERE obra_id = :NEW.id;
END IF;
END;
/

--PLSQL PARA VERIFICAR SI LA OBRA YA HA TERMINADO
BEGIN
   DBMS_SCHEDULER.create_job (
      job_name        => 'actualizar_trabajadores_fecha_fin_job',
      job_type        => 'PLSQL_BLOCK',
      job_action      => 'BEGIN
                           UPDATE trabajador
                           SET obra_id = NULL
                           WHERE obra_id IN (SELECT id FROM obra WHERE fecha_fin <= SYSDATE);
                         END;',
      start_date      => SYSTIMESTAMP,
      repeat_interval => 'FREQ=DAILY; BYHOUR=0; BYMINUTE=0; BYSECOND=0', -- Ejecutar una vez al día a medianoche
      enabled         => TRUE
   );
END;
/

-- Función para contar la cantidad de trabajadores en una obra específica
CREATE OR REPLACE FUNCTION contar_trabajadores(obra_id IN INTEGER) RETURN INTEGER IS
    cantidad INTEGER;
BEGIN
SELECT COUNT(*)
INTO cantidad
FROM trabajador
WHERE obra_id = contar_trabajadores.obra_id;

RETURN cantidad;
END contar_trabajadores;
/

-- Vista para mostrar trabajadores activos con los detalles de la obra
CREATE OR REPLACE VIEW trabajadores_activos_v AS
SELECT
    t.id AS trabajador_id,
    t.nombre AS trabajador_nombre,
    t.apellido_1 AS trabajador_apellido_1,
    t.apellido_2 AS trabajador_apellido_2,
    t.edad,
    t.tipo,
    o.id AS obra_id,
    o.nombre AS obra_nombre,
    o.ubicacion AS obra_ubicacion,
    o.presupuesto AS obra_presupuesto,
    o.fecha_inicio AS obra_fecha_inicio,
    o.fecha_fin AS obra_fecha_fin
FROM
    trabajador t
        JOIN
    obra o ON t.obra_id = o.id
WHERE
    t.estado = 1;
/

-- DOS TABLAS
-- UNA FUNCIÓN
-- UNA VISTA