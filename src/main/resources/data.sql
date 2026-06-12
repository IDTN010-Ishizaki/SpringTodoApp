INSERT INTO todos (title, detail, deadline, task_status, user_id) VALUES ('技術検証', 'SkiaSharpとOpenTKを使ってOpenVR Overlayにテクスチャを転送できるか検証します', '2026-04-20', 'COMPLETED', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, task_status, user_id) VALUES ('レンダリングエンジンの作成', 'SkiaSharpとOpenTK、OpenVRを使ってレンダリングエンジンを作成します', '2026-05-01', 'COMPLETED', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, task_status, user_id) VALUES ('Steam VRのシャットダウンとの同期', 'SteamVRがシャットダウンされた場合にイベントを受け取りシャットダウン出来るようにします', '2026-05-01', 'COMPLETED', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('レイヤーツリーの作成', '再帰的に描画コマンドを作成するLayerTreeを作成します', '2026-05-14', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('オブジェクトツリーの作成', '再帰的にLayerTreeを作成するRenderTreeを作成します', '2026-05-14', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('ElementTreeの作成', '無駄な再描画がされないように差分検知を行うElementTreeを作成します', '2026-6-9', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('WidgetTreeの作成', 'ElementTreeの設定を保持できるようにrecordを使ってWidgetTreeを作成します', '2026-06-9', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('R3をリビルドに組み込み', 'Observale.OnNextで再描画通知できるようにします', '2026-06-14', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('Hooksの作成', 'context.UseState<T>でElementにReactivePropertyを登録できるようにします', '2026-05-20', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('OpenVR入力の受け取り', 'context.UseState<T>でElementにReactivePropertyを登録できるようにします', '2026-06-22', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('Listenerの作成', 'ヒットテストを受け取るListenerを作成します', '2026-06-24', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('Starlightでドキュメント作成', 'Getting Stared, Widget catalog, Architectureを記述します', '2026-06-26', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('IO Thread Runnerの作成', 'async/awaitをWidgetから実行できるようにします', '2026-06-30', 'FloatSoda');
INSERT INTO todos (title, deadline, user_id) VALUES ('SKImageを使った画像の表示', '2026-06-30', 'FloatSoda');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('画像の表示の非同期化', 'IOThread RunnerからRender Thuread Runnerに画像を転送、Open GLのテクスチャを作成するようにします', '2026-07-07', 'FloatSoda');

INSERT INTO todos (title, detail, deadline, user_id) VALUES ('技術検証', 'Rust, WASM, Preact, Skiaを使ってレンダリングエンジンを作れるか検証します', '2026-07-07', 'Fluorite');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('Open XR のドキュメントの読み込み', 'Questでアプリを作るため、OpenXRのドキュメントを調査します', '2026-07-07', 'Fluorite');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('Rustの勉強', 'Rust Tourを一通り読みます', '2026-07-07', 'Fluorite');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('Rustでレンダリングエンジンの作成', 'skia-safeを使ってウィンドウに矩形を描画します', '2026-07-07', 'Fluorite');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('PreactでカスタムDOMの作成', 'PreactでどのようにDOMが作成されているか調査します', '2026-07-07', 'Fluorite');
INSERT INTO todos (title, detail, deadline, user_id) VALUES ('WITの勉強とレンダリングコマンド転送の実装', 'WITを使いPreactからRustに転送するためのインターフェースを定義します', '2026-07-07', 'Fluorite');

INSERT INTO todos (title, deadline, user_id) VALUES ('Memory Pack, Qyeryable, B木を使ってデータベースを作れるか検証', '2026-08-07', 'mini-ddb');
INSERT INTO todos (title, deadline, user_id) VALUES ('Memory Packを使ってファイルに保存してみる', '2026-08-07', 'mini-ddb');
INSERT INTO todos (title, deadline, user_id) VALUES ('Expressoinsを使ったクエリプランの組み立て', '2026-08-07', 'mini-ddb');

INSERT INTO todos (title, deadline, user_id) VALUES ('C#でNDArray<T>を作成', '2026-12-1', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('ReadonrySpan<T>を使って可変長インデクサの作成', '2026-12-1', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('C#でNDArray<T>を継承しTensor<T> where T : INumber<T>を作成', '2026-12-10', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('計算グラフの作成', '2026-12-20', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('ILGPU カーネルの作成', '2027-01-10', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('Moduleの作成', '2027-01-25', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('DataLoaderの作成', '2027-2-1', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VAlUES ('Skiaを使ってTransformを実装', '2027-02-10', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('Generic Hostを使った学習ループの作成と管理', '2027-02-20', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('MNIST を回してみる', '2027-03-01', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('Roslyn Analyzerを使ったテンソル形状不一致の検出', '2027-04-01', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('U-Netの作成とPascalVOCタスクを解いてみる', '2027-4-10', 'DimSharp');
INSERT INTO todos (title, deadline, user_id) VALUES ('MessagePackをつかってモデルのシリアライズとデシリアライズ', '2027-04-20', 'DimSharp');

INSERT INTO todos (title, deleted_at, user_id) VALUES ('ニートとして親のスネをかじる', '2026-04-01', 'user');

INSERT INTO users (id, password) VAlUES ('FloatSoda', '0p3nVR-0e3l4y-D3cl4rat1v3-UI');
INSERT INTO users (id, password) VALUES ('Fluorite', '0p3nXR-St4nd4l0n3-VR-UI');
INSERT INTO users (id, password) VALUES ('DimSharp', 'ML-FW-St4t1c-gr4ph-4nd-ILGPU');
INSERT INTO users (id, password) VALUES ('mini-ddb', 'MemoryPackAndExpressionsDBEngine');
INSERT INTO users (id, password) VALUES ('user', 'user');

UPDATE users SET password=RAWTOHEX(HASH('SHA-256', password));