PROCESO CREAR PROSPECTO PASO 2 DATOS DE CONTACTO
Microservicio: ms-prospects-cdt
TRX: PEF1("ingresoAltaPersonaNatural")
Api: POST 'https://ms-prospects-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v1/prospects' 
curl --location --request POST 'https://ms-prospects-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v1/prospects' \
--header 'Content-Type: application/json' \
--header 'x-santander-client-id: 1234' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJvZHMiLCJhdWQiOiJPbmJvYXJkaW5nIiwic2NvcGUiOiJPbmJvYXJkaW5nIiwiaXNzIjoib2RzIiwiaWF0IjoxNzAxNDQyNDg4LCJleHAiOjE3MDE0NDI3ODgsImp0aSI6IiIsIm5iZiI6MTcwMTQ0MjQ4OCwiY2lkIjoiNjM0ZWExZDYwY2YyNmUxMjAxOGFhNWZkIiwiYXR0UGVyc29uTnVtYmVyVHlwZSI6IiIsImF0dFBlcnNvbk51bWJlckNvZGUiOiIiLCJhY3IiOiIwIn0.o-mwPhCCPWLICyBvebFC2XOkEI8f975ET3dsKn6dePgJ1iALcYWVUb8-zMI_pmqzB024JeiPB6-oDqMUBR42-cFv_Hq3uWe8sOCpJ4kGXqm7YJIonvC6fNJJ5vXUiQzEW1BuzqNjSyuVPI55G58oGaluJVlCGK5E7G2N1S4TgPab1z8JcfeHjtecrb3pTTODrv3Dzpcp49y_6XB-V4243AJNB9hcRQawYsuWvXILZCz-pIldUKXWRdDETq4xrvfPb8J4LkrRbKF9lOZ9P6KbhBk7u-IgTC-GlA7XQLAP07-JzpANFF__kaM8Pp9QRTYuZ96AG8328SseeVvnlinXSA' \
--header 'Cookie: 98f1d649826a69aae0025c63772eaf9c=4ef578c28a3b930044782e937321e7b1' \
--data-raw '{
    "person": {
        "personName": {
            "givenName": "PRUEBAA",
            "lastName": "BITHDATEE",
            "secondLastName": "SOURCECODEEW"
        },
        "birthDate": "1987-11-06",
        "documents": [
            {
                "documentTypeCode": "CC",
                "documentNumber": "2700001216"
            }
        ]
    },
    "contactPoints": [
        {
            "phoneAddress": {
                "mobileNumber": "3017857724",
                "internationalCode": "57",
                "phoneNumber": "3017857724"
            },
            "electronicAddress": {
                "emailAddress": "LMHJ235@GMAIL.COM"
            }
        }
    ]
}'


Internamente consume la API  del ms-serviceengine-cdt 
curl --location --request POST 'http://ms-serviceengine-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/service-engine/procesar/ingresoAltaPersonaNatural?idFormulario=ingresoAltaPersonaNatural' \
--header 'x-santander-client-id: 123' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE3NzY3ODEzMDcsIm5iZiI6MTc3Njc4MTMwNiwiZXhwIjoxNzc2NzgxODc3LCJqdGkiOiIyNjE1MWExNC04Y2YyLTRlOTUtYWFjNy0zNjQzYjdiNDhiZTgiLCJpc3MiOiJDT19PRFMiLCJhZ2VudFVzZXJJRCI6IiIsImNpZCI6ImI1YmM1ZDEyLWUzNmUtNDc0Zi1hZDU0LTAzYTNhZGFkZTNhZCIsImFjciI6IjUiLCJjbGllbnRfaWQiOiIyNjNlYzE0NiIsImF1ZCI6Ik9uYm9hcmRpbmciLCJzY29wZSI6Ik9uYm9hcmRpbmciLCJjaWRfdHAiOiJiNWJjNWQxMi1lMzZlLTQ3NGYtYWQ1NC0wM2EzYWRhZGUzYWQifQ.e9du69EzDaK8EHfG6tKhOIBbAy-LzJd7hZI0ceAa85teATb2xDkWwdfKWzmkqrr82DjJBQRVXsuBWkZip1kll9UwO8iD4JVXaq7NiwW7xCoTqlc9R8z4qf74OKXEAH8Dy00a0xQcOWL8Y-ZxFRoIRR2oCiTEWT_EgRa7QWu_BaqPHuSm7ZXtca8vYjoMTs8s-yoRXqlud9jJ2rGr9TFgB82D0BBAmjOTz7XlWyjqAp4i-ZyzYzRWUX_yfo7Ic5J2iuzqQE8iIDlCtBdB6WxhVcARx9yCfWPu4PyEU699YhsxkoJJfOhmFTwnycxLMvN1aaYv0AkcjHVzfLQkpsxwcQ' \
--header 'Content-Type: application/json' \
--header 'Cookie: fdca9cc6f868512630d4a1c1deb3d2a0=b60ca17b812cf171a7848096203b2353' \
--data-raw '{
    "cabecera": {
        "rutaServicio": "ingresoAltaPersonaNatural",
        "sesion": {
            "usuario": "@NETE781",
            "terminal": "",
            "horaConexion": "2026-05-05T16:07",
            "entorno": "N",
            "perfil": "GCAJASTL",
            "sucursal": "0100",
            "entidad": "0065",
            "diasRestantesCambioClave": "29",
            "fechaContable": "2026-05-05",
            "turno": ""
        },
        "funcion": "Intro",
        "secuencia": 44204,
        "canal": "60"
    },
    "data": {
        "datosBasicos": {
            "tipoIdentificacion": "CC",
            "numeroIdentificacion": "2700001216",
            "nombre": "PRUEBAA",
            "primerApellido": "BITHDATEE",
            "segundoApellido": "SOURCECODEEW",
            "paisExpedicion": "COL",
            "ciudadExpedicion": "99999",
            "lugardeExpDescripcion": "",
            "fechaExpedicion": "2026-05-05",
            "paisNacimiento": "COL",
            "nacionalidad": "COL",
            "ciudadNacimiento": "05101",
            "lugardeNacimiento": "",
            "fechaNacimiento": "1987-11-06",
            "sexo": "M",
            "paisDireccion": "COL",
            "departamento": "05",
            "ciudad": "05101",
            "tipoVia": "NN",
            "nombreVia": "No informado",
            "descripcionDireccion": "NO INFORMADO",
            "clase": "004",
            "indicativo": "57",
            "telefono": "3017857724",
            "precelular": "57",
            "celular": "3017857724",
            "email": "LMHJ235@GMAIL.COM",
            "autorizoTelefono": false,
            "autorizacionEmail": false,
            "agrofic": "10000001",
            "codact": "",
            "codpaip": "COL",
            "conper": "",
            "domant": "",
            "entpre": "",
            "estciv": "",
            "estper": "",
            "estrat": "",
            "fecalt": "2024-01-09",
            "fecfal": "9999-12-31",
            "hstamp": "",
            "hstamp2": "",
            "hstamp3": "",
            "hstamp4": "",
            "hstamp5": "",
            "logdomp": "",
            "logtelp": "",
            "numper": "",
            "profes": "",
            "seccel": "",
            "secdoc": "",
            "secdomp": "",
            "secdotc": "",
            "secdotp": "",
            "secema": "",
            "sectelp": "",
            "sucadm": "0100",
            "sucmod": "",
            "termod": "",
            "tipdomp": "PRI",
            "tipper": "F",
            "tiptelp": "001",
            "usualt": "",
            "usumod": ""
        }
    }
}'

Y el ms-serviceengine-cdt consume ms-frameengine-cdt, este ms es el encargado de consumir IBM MQ (Colas) para llegar hasta Altair, esto genera que el usuario sea creado como prospecto como respuesta se recibe prospectId o penumper


paso de PRO(Prospecto) a NCL(No Cliente) PASO 6 DATOS ECONOMICOS KYC
micorservicio ms-customer-cdt 
trx: PEF2(modificarMantencionPersonaNaturalDatosBasicos)
Api: PUT 'https://ms-customer-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v3/customers/{penumper}

este cambio de estado de PRO(Prospecto) a NCL(No Cliente). se realiza despues de consumir KYC y que el prospecto cumple con las politicas de no encontrarse en listas restrictcivas 
consumiendo el api PUT pasando el penumper en el path y en el body solo los datos personales y entregando como codigo http de respuesta un 204
curl --location --request PUT 'https://ms-customer-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v3/customers/{penumper}' \
--header 'x-santander-client-id: asdasdasd' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJwcnVlYmEiLCJpYXQiOjE3NzQzNjQ5NjZ9.Eh8wIYix4sm54YFpYyXUaRKkT_2_zCv9JGSPpetQ9TZXk1jaSM9P9YBHXM_zyGaOGnYKuVRE0bcUrC3X4rDqjtCv5cdi88Scw5vuBtXgdlrRfQqufLj7Qx6XcT1wt8kriNIadDXNws6XwWF82KeXNg_ZauC-dLLJzSX-ynw37SkAY99YfwJ9Y_OKnj69OdCpyszFzlUs3HMcR5jp8CYKtSUDHZUs7jGniE_SpNBBzC5KVqllMk4U5pinjGuRZ7ZpmGivmOXDFJu48zR6jfHUT44Q4ln0HfWKhdRL9TsveUr3aEae6PIdmjqpPh0oAQZvWCOB07Lc1AkxT16LOqOk9Q' \
--header 'Content-Type: application/json' \
--header 'Cookie: 32fc4d3da987d1e0dac88fb09efe02e8=0005d3c3dce8fda1506513aade5a444f' \
--data-raw '{
    "person": {
        "personName": {
            "givenName": "PRUEBA",
            "lastName": "BITHDATE",
            "secondLastName": "SOURCECODE",
            "fullName": "PRUEBA BITHDATE SOURCECODE"
        }
    }
}'

al consultar ese penumper en prospect yo no le debe enconytrar al contrario si lo consultamos en customer nos debe arrojar que una respuesta con los datos de ese cliente 


paso de NCL(No Cliente) a CL(Cliente) PASO 7 APERTURA CDT

para pasar de No cliente a cliente se debe constituir un CDT por lo tanto primero se simula el CDT consumiendo la api 

curl --location --request POST 'https://ms-termdeposits-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v1/term_deposits/simulate_placement' \
--header 'x-santander-client-id: a1b30a84-7bf3-442e-84a0-e935d8163b5a' \
--header 'Authorization: Bearer 1234' \
--header 'Content-Type: application/json' \
--header 'Cookie: e0305693345d185b10ad1ab38a9b0d40=ae939511b032b8560f8eeb24f2333002' \
--data-raw '
{
    "product": {
        "productCode": "04",
        "subproduct": {
            "subproductId": "0250"
        }
    },
    "amount": {
        "amount": "26032026,00"
    },
    "periodicity": {
        "frequency": "90",
        "periodTypeCode": "D"
    },
    "settlementConditionCode": "V"
}'

y posterios se crea el CDT 

Microservicio: ms-term-deposit-funds
TRX: trxBP14
Api: 'https://ms-termdepositfunds-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v1/term_deposit_funds/00650100000040727988/placements/00001-00000/manage_funds
Asta api 
curl --location --request POST 'https://ms-termdepositfunds-cdt-core-dev.apps.ocp4-preprod.cosanpre.corp/v1/term_deposit_funds/00650100000040727988/placements/00001-00000/manage_funds' \
--header 'x-santander-client-id: test' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer test' \
--header 'Cookie: 0fdf38693accbb57cb4d268f7ccb19bb=486479adef30b3db96f60331a82fdd80' \
--data-raw '{
    "sourceFunds": {
        "otherSource": {
            "paymentReference": "999876"
        }
    }
}'

Al quedar constituido el cdt al cliente pasa de no cliente a cliente, este proceso no involucra procesos batch todo se realiza en linea 



