package com.example.dineriumaplicacion;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import android.graphics.Color;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReporteGeneral extends AppCompatActivity {

    TextView sumaTextView, diferenciaTextView, txtRepGastoMaximo;
    Button btnRepDetalle, btnRepVolver;
    LineChart lineChart;
    CollectionReference transaccionesRef = FirebaseFirestore.getInstance().collection("tblTransaccion");
    CollectionReference objetivosRef = FirebaseFirestore.getInstance().collection("tblObjetivos");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_general);
        sumaTextView = (TextView) findViewById(R.id.txtRepTotalGastos);
        diferenciaTextView = (TextView) findViewById(R.id.txtRepTotalAhorro);
        txtRepGastoMaximo = (TextView) findViewById(R.id.txtRepGastoMaximo);
        btnRepDetalle = (Button) findViewById(R.id.btnRepDetalle);
        btnRepVolver = (Button) findViewById(R.id.btnRepVolver);
        ObtenerTotalGastos();
        ObtenerAhorroTotal();
        GraficoLineal();
        GraficoCircular();
        GastoMayor();

        btnRepDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirReporteDetalle();
            }
        });

        btnRepVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolverInicio();
            }
        });
    }

    public void GastoMayor(){
        // Realizar la consulta para encontrar el valor máximo de traMonto
        Query query = transaccionesRef.orderBy("traMonto", Query.Direction.DESCENDING).limit(1);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                    Double maxValue = documentSnapshot.getDouble("traMonto");
                    if (maxValue != null) {
                        txtRepGastoMaximo.setText(String.valueOf(maxValue));
                    }
                }
            }
        });
    }

    public void GraficoLineal()
    {
        lineChart = (LineChart) findViewById(R.id.lineChart);

        // Crear un conjunto de datos con valores de ejemplo
        LineDataSet dataSet = new LineDataSet(getData(), "Ejemplo de datos");
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLACK);

        // Crear una lista de conjuntos de datos para agregar al gráfico
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        // Crear un objeto LineData con la lista de conjuntos de datos
        LineData lineData = new LineData(dataSets);

        // Configurar el gráfico con los datos creados
        lineChart.setData(lineData);
        lineChart.invalidate(); // Actualizar el gráfico
    }

    private ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(0, 0));
        data.add(new Entry(1, 4));
        data.add(new Entry(2, 2));
        data.add(new Entry(3, 6));
        data.add(new Entry(4, 8));
        data.add(new Entry(5, 4));
        return data;
    }

    public void AbrirReporteDetalle(){
        //Intent Detalle = new Intent(this, com.example.dineriumaplicacion.ReporteDetalle.class);
        //startActivity(Detalle);
    }

    public void VolverInicio(){
        Intent Inicio = new Intent(this, com.example.dineriumaplicacion.InicioProceso.class);
        startActivity(Inicio);
    }

    public void GraficoCircular(){
        // Evita que el teclado se abra automáticamente al iniciar la actividad
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        PieChart chart = (PieChart) findViewById(R.id.chartPieReporte);
        // Datos de ejemplo

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, 8));
        entries.add(new PieEntry(50f, 4));
        entries.add(new PieEntry(20f, 13));

        PieDataSet dataSet = new PieDataSet(entries, "Ejemplo de gráfico circular");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##0")));
        chart.setData(data);
        chart.setEntryLabelColor(Color.BLACK);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setDrawHoleEnabled(false);
        chart.invalidate();
    }

    public void ObtenerTotalGastos(){
        transaccionesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //Obtiene los resultados de una consulta a la base de datos
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    double suma = 0;
                    for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                        // Obtener el valor del campo "traMonto" y sumarlo
                        double monto = documentSnapshot.getDouble("traMonto");
                        suma += monto;
                    }
                    sumaTextView.setText(String.valueOf(suma));
                }
            } else {
                sumaTextView.setText("Error al obtener los datos");
            }
        });
    }

    public void ObtenerAhorroTotal(){
        objetivosRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //Obtiene los resultados de una consulta a la base de datos
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0); //PrimerDocumento de la lista
                    String presupuestoString = documentSnapshot.getString("objPresupuesto");
                    double presupuestoObjetivos = Double.parseDouble(presupuestoString);

                    // Obtener todos los documentos de la colección "tblTransaccion"
                    transaccionesRef.get().addOnCompleteListener(transaccionesTask -> {
                        if (transaccionesTask.isSuccessful()) {
                            QuerySnapshot transaccionesQuerySnapshot = transaccionesTask.getResult();
                            if (transaccionesQuerySnapshot != null) {
                                double suma = 0;
                                for (DocumentSnapshot transaccionDocument : transaccionesQuerySnapshot.getDocuments()) {
                                    // Obtener el valor del campo "traMonto" y sumarlo
                                    double monto = transaccionDocument.getDouble("traMonto");
                                    suma += monto;
                                }

                                // Calcular la diferencia entre el presupuesto de objetivos y la suma de montos
                                double diferencia = presupuestoObjetivos - suma;
                                diferenciaTextView.setText(String.valueOf(diferencia));
                            }
                        } else {
                            diferenciaTextView.setText("Error al obtener los datos");
                        }
                    });
                }
            } else {
                diferenciaTextView.setText("Error al obtener los datos");
            }
        });
    }
}