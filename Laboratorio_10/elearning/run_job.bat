@echo off
echo Iniciando procesamiento de datos en HTCondor
echo Fecha y hora de inicio: %date% %time%

# Descomprimir los materiales del curso
powershell -command "Expand-Archive -Path course_materials.zip -DestinationPath course_materials"

# Procesar los datos de los estudiantes
java ProcessStudentData student_data.csv processed_data.csv

# Generar informes
java GenerateReports processed_data.csv reports/

echo Procesamiento completado
echo Fecha y hora de finalización: %date% %time%
