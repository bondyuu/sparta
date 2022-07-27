from pymongo import MongoClient, collection
import jwt
import certifi
import datetime
import hashlib
from flask import Flask, render_template, jsonify, request, redirect, url_for
from datetime import datetime, timedelta
from bson.objectid import ObjectId
import requests

app = Flask(__name__)
SECRET_KEY = 'SPARTA'
ca = certifi.where()

client = MongoClient('mongodb+srv://leo4902:wnwkd0919!@cluster0.57geb.mongodb.net/Cluster0?retryWrites=true&w=majority',
                     tlsCAFile=ca)
db = client.dbsparta

@app.route('/')
def home():
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user_info = db.users.find_one({"username": payload["id"]})
        return render_template('index.html', user_info=user_info)
    except jwt.ExpiredSignatureError:
        return redirect(url_for("login", msg="로그인 시간이 만료되었습니다."))
    except jwt.exceptions.DecodeError:
        return redirect(url_for("login", msg="로그인 정보가 존재하지 않습니다."))

@app.route("/db_data_save")
def data():
    url = "http://openapi.seoul.go.kr:8088/4a6a484f496c656f33336a5167796d/json/culturalEventInfo/1/500/"
    response = requests.get(url).json()['culturalEventInfo']['row']

    for row in response:
        doc = {
            'codename': row["CODENAME"],
            'title': row["TITLE"],
            'guname': row["GUNAME"],
            'main_img': row["MAIN_IMG"],
            'use_trgt': row["USE_TRGT"],
            'program': row["PROGRAM"]
        }
        db.seoul.insert_one(doc)

    return jsonify({'msg': 'db 저장 완료'})

@app.route("/search",methods=["POST"])
def search():

    codename_receive = request.form['codename_give']
    guname_receive = request.form['guname_give']

    if (codename_receive != "") and (guname_receive == ""):
        search_result = list(db.seoul_data.find({'codename': codename_receive}))
        for result in search_result:
            result['_id'] = str(result['_id'])
    elif (codename_receive == "") and (guname_receive != ""):
        search_result = list(db.seoul_data.find({'guname': guname_receive}))
        for result in search_result:
            result['_id'] = str(result['_id'])
    elif (codename_receive != "") and (guname_receive != ""):
        search_result = list(db.seoul_data.find({'codename': codename_receive, 'guname': guname_receive}))
        for result in search_result:
            result['_id'] = str(result['_id'])

    return jsonify({'search_result':search_result})

@app.route("/show")
def show():

    show_result = list(db.seoul_data.find({}))

    for result in show_result:
        result['_id'] = str(result['_id'])

    return jsonify({'show_result':show_result})

@app.route("/comments")
def comment():

    id_receive = request.args.get("id")
    data = db.seoul_data.find_one({'_id': ObjectId(id_receive)}, {'_id': False})

    return render_template("comments.html", data=data, id=id_receive)

@app.route('/sign_up/check_dup', methods=['POST'])
def check_dup():
    username_receive = request.form['username_give']
    exists = bool(db.users.find_one({"username": username_receive}))
    return jsonify({'result': 'success', 'exists': exists})


@app.route('/login')
def login():
    msg = request.args.get("msg")
    return render_template('login.html', msg=msg)


@app.route('/sign_in', methods=['POST'])
def sign_in():
    # 로그인
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']

    pw_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    result = db.users.find_one({'username': username_receive, 'password': pw_hash})

    if result is not None:
        payload = {
            'id': username_receive,
            'exp': datetime.utcnow() + timedelta(seconds=60 * 60 * 24)  # 로그인 24시간 유지
        }
        token = jwt.encode(payload, SECRET_KEY, algorithm='HS256')

        return jsonify({'result': 'success', 'token': token, 'payload':payload['id']})
    # 찾지 못하면
    else:
        return jsonify({'result': 'fail', 'msg': '아이디/비밀번호가 일치하지 않습니다.'})


@app.route('/sign_up/save', methods=['POST'])
def sign_up():
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']
    password_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    doc = {
        "username": username_receive,  # 아이디
        "password": password_hash,  # 비밀번호
        "profile_name": username_receive,  # 프로필 이름 기본값은 아이디

    }
    db.users.insert_one(doc)
    return jsonify({'result': 'success'})

@app.route("/review", methods=["POST"])
def comment_done():
    login_id_receive = request.form["login_id"]
    id_receive = request.form["id"]
    rvcomment_receive = request.form["rvcomment_give"]

    doc = {
        'comment': rvcomment_receive,
        'id': id_receive,
        'writter':login_id_receive,
    }
    db.reviews.insert_one(doc)
    return jsonify({'msg': '등록 완료!'})

@app.route("/review2", methods=["POST"])
def mars_get():
    id_receive = request.form["id"]
    comments_list = list(db.reviews.find({'id':id_receive},{'_id':False}))
    return jsonify({'comment':comments_list})

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)