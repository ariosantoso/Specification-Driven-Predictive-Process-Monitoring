def build_and_evaluate(m, outputModelFile, test_list):
    model = m[1]
    model.fit(training, target)

    modelType = type(model).__name__

    # save the model to disk
    if type(model).__name__ == 'KerasClassifier':
        model.model.save(outputModelFile + m[0]+ '.hd5')

    else:
        filename = outputModelFile + m[0]+ '.sav'
        pickle.dump(model, open(filename, 'wb'))

    #loaded_model = pickle.load(open(filename, 'rb'))

    this_result = []

    for tup in test_list:
        testdata = tup[1]
        actual_str = tup[2]
        
        actual = np.asarray(actual_str)
        target_classes = model.classes_.tolist()
        #target_classes = loaded_model.classes_.tolist()
        actual_num = [target_classes.index(l) for l in actual_str]
        
        predicted = model.predict(testdata)
        pred_proba = model.predict_proba(testdata)
        #predicted = loaded_model.predict(testdata)
        #pred_proba = loaded_model.predict_proba(testdata)
        
        accuracy = metrics.accuracy_score(actual, predicted)
        roc_auc_0_none = metrics.roc_auc_score(actual_num, pred_proba[:,0])
        roc_auc_1_none = metrics.roc_auc_score(actual_num, pred_proba[:,1])
        fpr, tpr, threshold = metrics.roc_curve(actual, model.predict_proba(testdata)[:,1], pos_label=target_classes[1])
        fpr0, tpr0, threshold0 = metrics.roc_curve(actual, model.predict_proba(testdata)[:,0], pos_label=target_classes[0])
        #fpr, tpr, threshold = metrics.roc_curve(actual, loaded_model.predict_proba(testdata)[:,1], pos_label=target_classes[1])
        #fpr0, tpr0, threshold0 = metrics.roc_curve(actual, loaded_model.predict_proba(testdata)[:,0], pos_label=target_classes[0])
        auc1 = metrics.auc(fpr, tpr)
        auc0 = metrics.auc(fpr0, tpr0)
        
        clf_report = metrics.classification_report(actual, predicted)
        precisions = metrics.precision_score(actual, predicted, average=None)
        weighted_precision = metrics.precision_score(actual, predicted, average='weighted')
        recalls = metrics.recall_score(actual, predicted, average=None)
        weighted_recall = metrics.recall_score(actual, predicted, average='weighted')
        f1_scores = metrics.f1_score(actual, predicted, average=None)
        weighted_f1 = metrics.f1_score(actual, predicted, average='weighted')
        prf = metrics.precision_recall_fscore_support(actual, predicted, average=None)

        eval_result = dict(testname=tup[0], accuracy=accuracy, 
                           labels=model.classes_.tolist(),
                           roc_auc_0=roc_auc_0_none, roc_auc_1=roc_auc_1_none,
                           auc1=auc1, auc0=auc0, precisions=precisions,
                           weighted_precision=weighted_precision,
                           recalls=recalls, weighted_recall=weighted_recall,
                           f1_scores=f1_scores, weighted_f1=weighted_f1,
                           support=prf[3], params=model.get_params(), modelType=modelType)

        this_result.append(eval_result)

    return this_result