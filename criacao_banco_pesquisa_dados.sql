--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-09-09 11:56:12 BRT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2857 (class 1262 OID 16468)
-- Name: pesquisa_dados; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE pesquisa_dados WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8';


ALTER DATABASE pesquisa_dados OWNER TO postgres;

\connect pesquisa_dados

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 172 (class 3079 OID 12670)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 172
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 16471)
-- Name: resultado_consulta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resultado_consulta (
    id_consulta integer NOT NULL,
    hora time without time zone,
    dados_csv text,
    texto_consultado character varying(256)
);


ALTER TABLE public.resultado_consulta OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16469)
-- Name: resultado_consulta_id_consulta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE resultado_consulta_id_consulta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resultado_consulta_id_consulta_seq OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 170
-- Name: resultado_consulta_id_consulta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE resultado_consulta_id_consulta_seq OWNED BY resultado_consulta.id_consulta;


--
-- TOC entry 2743 (class 2604 OID 16474)
-- Name: id_consulta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resultado_consulta ALTER COLUMN id_consulta SET DEFAULT nextval('resultado_consulta_id_consulta_seq'::regclass);


--
-- TOC entry 2745 (class 2606 OID 16479)
-- Name: resultado_consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resultado_consulta
    ADD CONSTRAINT resultado_consulta_pkey PRIMARY KEY (id_consulta);


--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-09-09 11:56:12 BRT

--
-- PostgreSQL database dump complete
--

