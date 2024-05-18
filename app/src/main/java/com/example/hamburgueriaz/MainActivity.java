package com.example.hamburgueriaz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText qtdBurgerEditText;
    private TextView valorTotalTextView;
    private CheckBox
            cheddarCheckBox,
            baconCheckBox,
            queijoCheckBox,
            alfaceCheckBox,
            tomateCheckBox,
            onionRingsCheckBox,
            maioneseCheckBox,
            ketchupCheckBox,
            mostardaCheckBox;
    private Button btnSomar, btnSubtrair, btnEnviarPedido;

    private static final double PRECO_HAMBURGUER = 20.0;
    private static final double PRECO_CHEDDAR = 1.0;
    private static final double PRECO_BACON = 2.0;
    private static final double PRECO_QUEIJO = 2.0;
    private static final double PRECO_ALFACE = 0.5;
    private static final double PRECO_TOMATE = 1.5;
    private static final double PRECO_ONION_RINGS = 3.0;
    private static final double PRECO_MAIONESE = 0.2;
    private static final double PRECO_KETCHUP = 0.2;
    private static final double PRECO_MOSTARDA = 0.2;

    int quantidade = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomeEditText = findViewById(R.id.nome);
        qtdBurgerEditText = findViewById(R.id.qtd_buger);
        valorTotalTextView = findViewById(R.id.valor_total);

        cheddarCheckBox = findViewById(R.id.cheddar);
        baconCheckBox = findViewById(R.id.bacon);
        queijoCheckBox = findViewById(R.id.queijo);
        alfaceCheckBox = findViewById(R.id.alface);
        tomateCheckBox = findViewById(R.id.tomate);
        onionRingsCheckBox = findViewById(R.id.onion_rings);
        maioneseCheckBox = findViewById(R.id.maionese);
        ketchupCheckBox = findViewById(R.id.ketchup);
        mostardaCheckBox = findViewById(R.id.mostarda);

        btnSomar = findViewById(R.id.btn_somar);
        btnSubtrair = findViewById(R.id.btn_subtrair);
        btnEnviarPedido = findViewById(R.id.btn_enviar_pedido);

        qtdBurgerEditText.setText(String.valueOf(quantidade));

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                somarQuantidade();
            }
        });

        btnSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtrairQuantidade();
            }
        });

        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarPedido();
            }
        });
    }

    private void somarQuantidade() {
        quantidade++;
        if (quantidade > 0) {
            qtdBurgerEditText.setText(String.valueOf(quantidade));
        } else {
            quantidade = 1;
            qtdBurgerEditText.setText(String.valueOf(quantidade));
        }
    }

    private void subtrairQuantidade() {
        quantidade--;
        if (quantidade > 0) {
            qtdBurgerEditText.setText(String.valueOf(quantidade));
        } else {
            quantidade = 1;
            qtdBurgerEditText.setText(String.valueOf(quantidade));
        }
    }

    private void enviarPedido() {
        String nome = nomeEditText.getText().toString();

        double precoTotal = PRECO_HAMBURGUER * quantidade;

        double adicionais = 0.0;

        if (cheddarCheckBox.isChecked()) {
            adicionais += PRECO_CHEDDAR;
        }
        if (baconCheckBox.isChecked()) {
            adicionais += PRECO_BACON;
        }
        if (queijoCheckBox.isChecked()) {
            adicionais += PRECO_QUEIJO;
        }
        if (alfaceCheckBox.isChecked()) {
            adicionais += PRECO_ALFACE;
        }
        if (tomateCheckBox.isChecked()) {
            adicionais += PRECO_TOMATE;
        }
        if (onionRingsCheckBox.isChecked()) {
            adicionais += PRECO_ONION_RINGS;
        }
        if (maioneseCheckBox.isChecked()) {
            adicionais += PRECO_MAIONESE;
        }
        if (ketchupCheckBox.isChecked()) {
            adicionais += PRECO_KETCHUP;
        }
        if (mostardaCheckBox.isChecked()) {
            adicionais += PRECO_MOSTARDA;
        }

        precoTotal += adicionais * quantidade;

        String temCheddar = cheddarCheckBox.isChecked() ? "Sim" : "Não";
        String temBacon = baconCheckBox.isChecked() ? "Sim" : "Não";
        String temQueijo = queijoCheckBox.isChecked() ? "Sim" : "Não";
        String temAlface = alfaceCheckBox.isChecked() ? "Sim" : "Não";
        String temTomate = tomateCheckBox.isChecked() ? "Sim" : "Não";
        String temOnionRings = onionRingsCheckBox.isChecked() ? "Sim" : "Não";
        String temMaionese = maioneseCheckBox.isChecked() ? "Sim" : "Não";
        String temKetchup = ketchupCheckBox.isChecked() ? "Sim" : "Não";
        String temMostarda = mostardaCheckBox.isChecked() ? "Sim" : "Não";

        String resumoPedido = "Nome: " + nome + "\n" +
                "Tem Cheddar? " + temCheddar + "\n" +
                "Tem Bacon? " + temBacon + "\n" +
                "Tem Queijo? " + temQueijo + "\n" +
                "Tem Alface? " + temAlface + "\n" +
                "Tem Tomate? " + temTomate + "\n" +
                "Tem Onion Rings? " + temOnionRings + "\n" +
                "Tem Maionese? " + temMaionese + "\n" +
                "Tem Ketchup? " + temKetchup + "\n" +
                "Tem Mostarda? " + temMostarda + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Preço final: R$ " + String.format("%.2f", precoTotal);

        valorTotalTextView.setText(resumoPedido);


        String[] destinatarios = {"cliente@hamgurgueriaz.com"};
        String assunto = "Pedido de " + nomeEditText.getText().toString();
        String corpo = resumoPedido;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, destinatarios);
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(Intent.EXTRA_TEXT, corpo);

        startActivity(intent);

    }
}
