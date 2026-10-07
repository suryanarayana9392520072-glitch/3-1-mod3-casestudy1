
from flask import Flask, jsonify, request
import os
import uuid

app = Flask(__name__)
sessions = {}

@app.get("/health")
def health():
    return jsonify({
        "status": "ok",
        "service": "case1-python-backend"
    })

@app.post("/chat")
def chat():
    data = request.get_json(silent=True) or request.form.to_dict()

    message = str(data.get("message", "")).strip()
    session_id = str(data.get("session_id", "")).strip() or str(uuid.uuid4())

    if not message:
        return jsonify({
            "response": "",
            "session_id": session_id,
            "error": "message is required"
        }), 400

    history = sessions.setdefault(session_id, [])

    if message.lower() in {"hi", "hello", "hey", "hii", "hai"}:
        response = "Hello! The Case 1 Python backend is connected successfully."
    elif history:
        response = (
            f"You said: “{message}”. "
            f"I remember your previous message: “{history[-1]}”."
        )
    else:
        response = (
            f"You said: “{message}”. "
            "The Python Flask backend received your message successfully."
        )

    history.extend([message, response])
    sessions[session_id] = history[-12:]

    return jsonify({
        "response": response,
        "session_id": session_id,
        "error": None
    })

if __name__ == "__main__":
    app.run(
        host=os.getenv("HOST", "0.0.0.0"),
        port=int(os.getenv("PORT", "5000")),
        debug=False
    )
