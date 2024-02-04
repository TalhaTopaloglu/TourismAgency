PGDMP     5    *                |            tourismAgency    13.13    13.13 "    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16811    tourismAgency    DATABASE     l   CREATE DATABASE "tourismAgency" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE "tourismAgency";
                postgres    false            �            1259    16824    hotel    TABLE     �  CREATE TABLE public.hotel (
    id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_car_park boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16822    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            �            1259    16834    hotel_pension    TABLE     ~   CREATE TABLE public.hotel_pension (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
 !   DROP TABLE public.hotel_pension;
       public         heap    postgres    false            �            1259    16832    hotel_pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    205            �            1259    16844    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    16842    hotel_season_season_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    16861    reservation    TABLE     U  CREATE TABLE public.reservation (
    id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price text NOT NULL,
    guest_count text NOT NULL,
    guest_name text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL,
    guest_idno text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16859    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    211            �            1259    16851    room    TABLE     �  CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type text NOT NULL,
    adult_price text NOT NULL,
    child_price text NOT NULL,
    bed_capacity text NOT NULL,
    square text NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL,
    stock integer NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16849    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    209            �            1259    16814    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16812    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    201            �          0    16824    hotel 
   TABLE DATA           �   COPY public.hotel (id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_car_park, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_room_service) FROM stdin;
    public          postgres    false    203   �(       �          0    16834    hotel_pension 
   TABLE DATA           C   COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    205   �)       �          0    16844    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    207   d*       �          0    16861    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_mail, guest_phone, guest_idno) FROM stdin;
    public          postgres    false    211   �*       �          0    16851    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, adult_price, child_price, bed_capacity, square, television, minibar, game_console, cash_box, projection, stock) FROM stdin;
    public          postgres    false    209   @+       �          0    16814    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    201   ,       �           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 18, true);
          public          postgres    false    202            �           0    0    hotel_pension_pension_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.hotel_pension_pension_id_seq', 26, true);
          public          postgres    false    204            �           0    0    hotel_season_season_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.hotel_season_season_id_seq', 33, true);
          public          postgres    false    206            �           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 79, true);
          public          postgres    false    210            �           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 30, true);
          public          postgres    false    208            �           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 16, true);
          public          postgres    false    200            J           2606    16841     hotel_pension hotel_pension_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT hotel_pension_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.hotel_pension DROP CONSTRAINT hotel_pension_pkey;
       public            postgres    false    205            H           2606    16831    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    203            L           2606    16848    hotel_season hotel_season_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT hotel_season_pkey;
       public            postgres    false    207            P           2606    16868    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    211            N           2606    16858    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    209            F           2606    16821    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    201            �   �   x�}��
�0���S��Al��
��^���p�`���+��^�f�ND�/M�פJ�k��Σ;]+hC#a����6�L$��I^(����]�E(�)aQF
�|"�搶�N`�]���eH��]l���,����h[��f�F-�q'�yJ)ɢ�7ޒ6�u��1�E	cP�3�V�m�K�n>H�u��?j�|�p�
!^-ˀ�      �   �   x�m�=
�@��z�sq&?j)	X(�E�f0Y�I`�B.ck�3�%ۙ`�|��R���I*<H}3]S-<�m�S����n�0h�(PS\y��;)bۡ�+
�F�ֶl,�����ۻ��8���E�<%�W���t	*���q�L���~ľnt{��&�L      �   g   x�u�K�0�5�Eç�z���mL������B�6b?�#J+��3����¬lLc
�����3J�	ܟ�^�z��%:��m1�T7I�v�];"��&0"      �   U   x�37�42�4202�50"(��45100�4�,I-.12�P(.s�Vs�]C(�l�A�f�F��v���@�G��:c���� X�8m      �   �   x�e�A� EןSx33�WХ.�ڄ�hb�<��1�ˁjk5�b^�#�BX�nH,D`֧EK��!#�@�?��mz��)�:GpN1p��]=J�`X�㖺<3A��@f��4ڡ��˔�D�):�'F	�j����/�Є/1��0!�����l�s�\W㎨T����w�r�Q�"l�I?��b)Kgkc��.V�      �   =   x�34�,I-.�4426�tt��2�LL���Cq����W��B�\}����"1z\\\ ް     