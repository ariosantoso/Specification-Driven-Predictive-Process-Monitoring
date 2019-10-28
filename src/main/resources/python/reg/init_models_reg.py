numEstimator = 200


zeroR_reg = DummyRegressor(strategy='mean')
ln_reg = LinearRegression(fit_intercept=False)
dt_reg = DecisionTreeRegressor()
gb_reg = GradientBoostingRegressor()
rf_reg = RandomForestRegressor(n_estimators=numEstimator)
ada_reg = AdaBoostRegressor(n_estimators=numEstimator)
et_reg = ExtraTreesRegressor(n_estimators=numEstimator)


models = [('ZeroR', zeroR_reg), ('LinearRegression', ln_reg), ('DecisionTree', dt_reg),  ('GradientBoosting', gb_reg), ('RandomForest', rf_reg), ('AdaBoost', ada_reg), ('ExtraTrees', et_reg), ]
#models = [('ZeroR',zeroR_reg)]

#test_list = [('TEST WITH TRAINING INSTANCE', training, target),('EARLY PREDICTION', early_test_data, early_target),('MID PREDICTION', mid_test_data, mid_target),('LATE PREDICTION', late_test_data, late_target),('PREDICTION FOR ALL POSSIBLE PREFIXES', all_test_data, all_target)]
test_list = [('TEST WITH TRAINING INSTANCE', training, target),('PREDICTION FOR ALL POSSIBLE PREFIXES', all_test_data, all_target)]
