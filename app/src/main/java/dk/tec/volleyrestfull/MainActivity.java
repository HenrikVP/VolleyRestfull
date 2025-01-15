package dk.tec.volleyrestfull;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ApiCalls api;

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

        api = new ApiCalls(this);
        Type carListType = new TypeToken<List<Car>>() { }.getType();
        api.getAll("car", carListType, new ApiCallBack<List<Car>>() {
            @Override
            public void onSucces(List<Car> result) {
                Data.cars = result;
                ((TextView) findViewById(R.id.text)).setText(result.get(1).getName());
            }

            @Override
            public void onError(String error) {}
        });
    }
}