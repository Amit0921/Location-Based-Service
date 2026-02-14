--
-- PostgreSQL database dump
--

-- Dumped from database version 14.8
-- Dumped by pg_dump version 15.2

-- Started on 2026-02-14 14:38:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2 (class 3079 OID 156328)
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- TOC entry 4247 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 157421)
-- Name: place; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.place (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(100),
    location public.geography(Point,4326) NOT NULL
);


ALTER TABLE public.place OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 157420)
-- Name: place_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.place_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_id_seq OWNER TO postgres;

--
-- TOC entry 4248 (class 0 OID 0)
-- Dependencies: 215
-- Name: place_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.place_id_seq OWNED BY public.place.id;


--
-- TOC entry 4088 (class 2604 OID 157424)
-- Name: place id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place ALTER COLUMN id SET DEFAULT nextval('public.place_id_seq'::regclass);


--
-- TOC entry 4240 (class 0 OID 157421)
-- Dependencies: 216
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.place (id, name, type, location) FROM stdin;
1	Cafe Coffee Day	cafe	0101000020E6100000329522ADA8735540C72A0DF995573440
2	Monkey Cafe	cafe	0101000020E6100000A9AB63EEC9735540F4B033B90F583440
3	Blueberry Cafe	cafe	0101000020E6100000AB1743EBE27355407B0CBA3EEC573440
4	Doppio Cafe	cafe	0101000020E6100000F8F686F6D1735540D7D143D9DC573440
5	Bocca Cafe	cafe	0101000020E610000007F28935CE7455404DF978E20A593440
6	Kruti Cafe	cafe	0101000020E6100000A64467A9F17455409807F268375B3440
7	Velvet Beans Cafe	cafe	0101000020E61000002785EB466A7455406BD976B9BB523440
\.


--
-- TOC entry 4087 (class 0 OID 156645)
-- Dependencies: 211
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- TOC entry 4249 (class 0 OID 0)
-- Dependencies: 215
-- Name: place_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.place_id_seq', 7, true);


--
-- TOC entry 4094 (class 2606 OID 157428)
-- Name: place place_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_pkey PRIMARY KEY (id);


--
-- TOC entry 4092 (class 1259 OID 157429)
-- Name: idx_place_location; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_place_location ON public.place USING gist (location);


--
-- TOC entry 4246 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2026-02-14 14:38:13

--
-- PostgreSQL database dump complete
--

