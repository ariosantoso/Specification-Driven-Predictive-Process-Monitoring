def build_and_evaluate(m, outputModelFile, test_list):
    model = m[1]
    model.fit(training, target)

    modelType = type(model).__name__

    # save the model to disk
    if type(model).__name__ == 'KerasRegressor':
        model.model.save(outputModelFile + m[0]+ '.hd5')

    else:
        filename = outputModelFile + m[0]+ '.sav'
        pickle.dump(model, open(filename, 'wb'))

    #loaded_model = pickle.load(open(filename, 'rb'))

    this_result = []

    for tup in test_list:
        testdata = tup[1]
        actual = tup[2]

        predicted = model.predict(testdata)
        #predicted = loaded_model.predict(testdata)

        mae = metrics.mean_absolute_error(actual, predicted)
        rmse = sqrt(metrics.mean_squared_error(actual, predicted))
        #coeff_of_determination = metrics.r2_score(actual, predicted)
        #correlation_coeff = 0

        eval_result = dict( testname=tup[0], 
                            mae=mae, 
                            rmse=rmse, 
                            #coeff_of_determination=coeff_of_determination,
                            #correlation_coeff=correlation_coeff,
                            params=model.get_params(), modelType=modelType)

        this_result.append(eval_result)

    return this_result
