numEstimator = 200

zeroR_clf = DummyClassifier(strategy='most_frequent')
log_clf = LogisticRegression(random_state=0, solver='lbfgs', max_iter=300, multi_class='auto')
nb_clf = GaussianNB()
dt_clf = DecisionTreeClassifier()
gb_clf = GradientBoostingClassifier(max_features='sqrt', n_estimators=numEstimator, max_depth=None)
rf_clf = RandomForestClassifier(n_estimators=numEstimator)
ada_clf = AdaBoostClassifier(n_estimators=numEstimator)
et_clf = ExtraTreesClassifier(n_estimators=numEstimator)
vot_clf = VotingClassifier(estimators=[('dt_clf', dt_clf), ('rf_clf', rf_clf), ('ada_clf', ada_clf), ('etc_clf', et_clf)], voting='soft')


models = [('ZeroR',zeroR_clf), ('LogisticRegression',log_clf), ('NaiveBayes',nb_clf), ('DecisionTree',dt_clf), ('GradientBoosting',gb_clf), ('RandomForest',rf_clf), ('AdaBoost', ada_clf), ('ExtraTrees', et_clf), ('Voting', vot_clf), ]

#test_list = [('TEST WITH TRAINING INSTANCE', training, target),('EARLY PREDICTION', early_test_data, early_target),('MID PREDICTION', mid_test_data, mid_target),('LATE PREDICTION', late_test_data, late_target),('PREDICTION FOR ALL POSSIBLE PREFIXES', all_test_data, all_target)]
test_list = [('TEST WITH TRAINING INSTANCE', training, target),('PREDICTION FOR ALL POSSIBLE PREFIXES', all_test_data, all_target)]


