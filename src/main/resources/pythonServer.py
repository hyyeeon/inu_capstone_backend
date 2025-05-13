import os
import joblib
import numpy as np
from flask import Flask, request, jsonify
import xgboost

app = Flask(__name__)

MODEL_DIR = 'C:\\Users\\ruth1\\Documents\\INU\\캡스톤\\inu_capstone_backend\\src\\main\\resources\\model'

model_cache = {}
importance_cache = {}

def load_model(model_name):
    if model_name not in model_cache:
        model_path = os.path.join(MODEL_DIR, f"{model_name}.joblib")
        model = joblib.load(model_path)
        model_cache[model_name] = model
        booster = model.get_booster()
        importance_cache[model_name] = booster.get_score(importance_type='weight')
        importance1 = importance_cache[model_name]
        print(importance1)
        top_3 = sorted(importance1.items(), key=lambda item: item[1], reverse=True)[:3]
        print(top_3)
        top_keys = [key for key, _ in top_3]
        print(top_keys)

    return model_cache[model_name], importance_cache[model_name], top_3
@app.route('/predict/<model_name>', methods=['POST'])
def predict(model_name):
    print("요청은 잘 들어왔어")
    try:
        input_data = request.get_json()
        print(f"받은 데이터: {input_data}")

        if not isinstance(input_data, dict):
            return jsonify({'error': 'Invalid input format. Must be a JSON object.'}), 400

        feature_keys = [
            "building_sales", "area_sales", "resident_population", "single_household",
            "subway_users", "similar_businesses", "distance_same_franchise",
            "nearby_schools", "nearby_tourist_attractions", "nearby_buildings"
        ]

        # 모든 키가 있는지 확인
        missing_keys = [key for key in feature_keys if key not in input_data]
        if missing_keys:
            return jsonify({'error': f'Missing keys in input data: {missing_keys}'}), 400

        input_values = [float(input_data[key]) for key in feature_keys]
        input_array = np.array([input_values])

        model, importance, top_3 = load_model(model_name)
        prediction = model.predict(input_array)[0]

        explanation = generate_explanation(prediction, input_data, top_3)

        print("모델 예측도 잘 돌렸어 :", prediction)

        return jsonify({
            'prediction': int(prediction),
            'explanation': explanation
        })

    except FileNotFoundError:
        return jsonify({'error': f'Model \"{model_name}\" not found.'}), 404
    except Exception as e:
        print("서버 오류:", str(e))
        return jsonify({'error': str(e)}), 500
    
def generate_explanation(prediction, input_data, top_3):
    # importance에는 feature 이름이 들어오는데, 이게 'f0', 'f1'이 아닐 수 있음
    #sorted_importance = sorted(importance.items(), key=lambda x: x[1], reverse=True)[:3]
    #top_features = [f for f, _ in sorted_importance]
    
    #top_values = {}
    #for feature_name in top_features:
        #if feature_name in input_data:
            #top_values[feature_name] = input_data[feature_name]

    explanation = f"예측 매출: {int(prediction)}만원. 주요 요인: {top_3}"
    #explanation += ", ".join([f"{k}={v}" for k, v in top_values.items()])
    return explanation

    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
