from __future__ import print_function, division

import torch
import torch.nn as nn
from PIL import Image
from torch.autograd import Variable
import numpy as np
import sys


class Parser(nn.Module):

    def __init__(self):
        super(Parser, self).__init__()
        
        self.features = torch.nn.Sequential(
            torch.nn.Conv2d(1, 16, 3),
            torch.nn.BatchNorm2d(16),
            torch.nn.LeakyReLU(),
            torch.nn.MaxPool2d(2, stride=2),
            
            torch.nn.Conv2d(16, 32, 3),
            torch.nn.BatchNorm2d(32),
            torch.nn.LeakyReLU(),
            torch.nn.MaxPool2d(2, stride=2),
            
            torch.nn.Conv2d(32, 64, 3),
            torch.nn.BatchNorm2d(64),
            torch.nn.LeakyReLU(),
            torch.nn.MaxPool2d(2, stride=2),
        )
             
        self.middle = torch.nn.Sequential(
            torch.nn.Linear(64 * 5 * 44, 1000),
            torch.nn.BatchNorm1d(1000),
            torch.nn.LeakyReLU(),
            torch.nn.Dropout(p=0.5),
            
            torch.nn.Linear(1000, 100),
            torch.nn.BatchNorm1d(100),
            torch.nn.LeakyReLU(),
        )
        
        self.classification = torch.nn.Sequential(
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5),
            torch.nn.Linear(100, 5)
        )
        
        
    def forward(self, inputs):
        
        features = self.features(inputs)
        
        re_pool3 = features.view(-1 , 64 * 5 * 44)
        
        linear2 = self.middle(re_pool3)
        
        final = []
        for index, layer in enumerate(self.classification):
            final.append(layer(linear2))
        
        return {'final': final}


folder = sys.argv[1]

image = Image.open(sys.argv[2])
image = np.array(image).astype('float32') / 255
image = image[:, :, 0].reshape(1, 1, 58, 372)
image = torch.from_numpy(image)

model = Parser()
model.load_state_dict(torch.load(folder + '/cpu.pt'))
model.cpu()
model.eval()

inputs = Variable(image)
layers = model(inputs)
final = layers['final']

rez = []
for i in range(10):
	rez.append(final[i][0].max(0)[1].data[0])

print(''.join(str(e) for e in rez))